output "postgres_db_name" {
  value = postgresql_database.lars_soerlie_backend.name
}

output "postgres_bg" {
  value = postgresql_database.lars_soerlie_backend.id
}