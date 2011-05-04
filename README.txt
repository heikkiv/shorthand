Shorthand - Simple URL shortener

To run server type:

	ant run

To package into war-file type:

	ant package

To shorten url, run server and open:

	http://localhost:8080/shorten?url=URL_TO_SHORTEN

where URL_TO_SHORTEN is the url encoded url you want to shorten.
For example:

	http://localhost:8080/shorten?url=http%3A%2F%2F3dmark.com
	
The service will responde with the shortened url in the response body.

