Feature: Validating Place APIS

@AddPlace
Scenario Outline: Verify place is added sucessfully 
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" using "post" http request
Then Success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
|name    | language | address  |
|AAHouse | Blanguage| Caddress |
#|BBHouse | Elanguage| Faddress |

@deletePlace
Scenario:Verify Delete Place functionality is working
Given DeletePlace Payload
When user calls "deletePlaceAPI" using "post" http request
Then Success with status code 200
And "status" in response body is "OK"


