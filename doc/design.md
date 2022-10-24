## Goal
Create a Jar file which could be run in an environment with a Docker daemon to start a MongoDB server.

The configuration could be provided as an external file or jar parameters.

A set of opinionated configuration will be used if not overridden by the user.

Instructions on how to connect to the MongoDB server will be shown to the user.

The version of MongoDB will depend on the image version used in building the Jar. Users, however, can 
rebuild the Jar using the Java source code with specifying the wanted MongoDB version. 

## Opinionated Configurations

### Durable data



