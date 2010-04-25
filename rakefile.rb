require 'buildr/scala'

define 'metaquill' do
  project.version = '0.1.0'
  project.group = 'org.metaquill'
  compile.options.deprecation = true

  package :jar
end
