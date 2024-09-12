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

  backend "s3" {
    key="terraform.state"
    bucket="lars-soerlie-backend-terraform-state"
    region="eu-west-1"
    encrypt = true
  }

  required_version = ">= 1.9.5"
}

locals {
  db_password_name = "db_password"
}

provider "aws" {
  region     = "eu-west-1"
  access_key = var.access_token
  secret_key = var.secret_token
}

provider "postgresql" {
  host            = "127.0.0.1"
  port = 5233
  username        = data.terraform_remote_state.postgres.outputs.db_username
  password        = data.terraform_remote_state.postgres.outputs.db_password
  superuser       = false
  connect_timeout = 30 
}

data "terraform_remote_state" "postgres" {
  backend = "s3"
  config = {
    key    = "terraform.tfstate"
    bucket = "mapokens-terraform-state"
    region = "eu-west-1"
  }
}

resource "random_password" "db_password"{
  length           = 50 
  special          = true
  override_special = "_!%^"
}

resource "aws_secretsmanager_secret" "db_password" {
  name = local.db_password_name
}

resource "aws_secretsmanager_secret_version" "db_password" {
  secret_id = aws_secretsmanager_secret.db_password.id
  secret_string = random_password.db_password.result
}

data "aws_secretsmanager_secret" "db_password" {
  name = local.db_password_name
}

resource "postgresql_role" "lars_soerlie_backend" {
  name     = "lars_soerlie_backend"
  login    = true
  password = data.aws_secretsmanager_secret.db_password
}
resource "postgresql_database" "lars_soerlie_backend" {
  name = "lars_soerlie_backend"
}

