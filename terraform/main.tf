terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0.0"
    }

    postgresql = {
      source = "cyrilgdn/postgresql"
      version= "1.22.0"
    }

  }
  required_version = ">= 1.9.5"

}

provider "aws" {
  region     = "eu-west-1"
  profile    = "private"
}

provider "postgresql" {
  database        = "postgres"
  host            = data.terraform_remote_state.postgres.outputs.db_address
  port            = 5432
  username        = data.terraform_remote_state.postgres.outputs.db_username
  password        = data.terraform_remote_state.postgres.outputs.db_password
  connect_timeout = 30 
}

data "terraform_remote_state" "postgres" {
  backend = "s3"
  config = {
    key    = "terraform.tfstate"
    bucket = "mapokens-terraform-state"
    region = "eu-west-1"

    // Find out how to use the profile instead of access_key and secret_key
    profile    = "private"
  }
}

resource "postgresql_database" "lars_soerlie_backend" {
  name = "lars_soerlie_backend"

}

