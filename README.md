# FakeAM
Fake implementation of OpenAM interface for testing.

A spring-boot application that mimics some of the behaviour of ForgeRock's [OpenAM product](https://www.forgerock.com/platform/access-management/).

## Building

The application builds with gradle with the command `gradle package`.

## Supported Endpoints

### Login : `/json/authenticate`

     curl --request POST \
     --header "X-OpenAM-Username:user1" \
     --header "X-OpenAM-Password:password1" \
     --header "Content-Type: application/json" \
     --data "{}" \
     "localhost:8084/json/authenticate"


### Logout : `/json/sessions?_action=logout`

     curl --request POST \
     --header "iplanetDirectoryPro:invalidtoken" \
     --header "Content-Type: application/json" \
     --data "{}" \
     "localhost:8084/json/sessions?_action=logout"



### Validate : `/openam/identity/isTokenValid`

     curl --request POST \
     --header "Content-Type: application/json" \
     --data "tokenid=INSERT_VALID_TOKEN" \
     "localhost:8084/openam/identity/isTokenValid"

### Admin (not OpenAM) : `/admin/load`

     curl --request POST \
     --header "Content-Type: application/json" \
     --data "[{\"username\":\"user1\", \"password\":\"pass1\"}, {\"username\":\"user2\", \"password\":\"pass2\"}]" \
     "localhost:8084/admin/load"

Loads a JSON array of users into the application, users can have the following fields:

* username
* password
* token
