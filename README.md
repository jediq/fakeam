# FakeAM
Fake implementation of OpenAM interface for testing.

A spring-boot application that mimics some of the behaviour of ForgeRock's [OpenAM product](https://www.forgerock.com/platform/access-management/).

##Building

The application builds with gradle with the command `gradle package`.

##Endpoints

* Login : `/json/authenticate`

* Logout : `/json/sessions?_action=logout`

* Validate : `/openam/identity/isTokenValid`

* Admin (not OpenAM) : `/admin/load`

