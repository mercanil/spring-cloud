input {
  tcp {
      port => 5044
  }
}
output{
elasticsearch {
   hosts => ["http://elasticsearch:9200"]
   index => "applog"
}
stdout { codec => rubydebug }
}