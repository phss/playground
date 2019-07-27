package main

import (
	"context"
	"flag"
	"fmt"
	"io/ioutil"

	"golang.org/x/oauth2"
)

func main() {

	clientID := flag.String("clientId", "", "")
	clientSecret := flag.String("clientSecret", "", "")
	refreshToken := flag.String("refreshToken", "", "")

	ctx := context.Background()
	conf := &oauth2.Config{
		ClientID:     *clientID,
		ClientSecret: *clientSecret,
		Scopes:       []string{"read"},
		Endpoint: oauth2.Endpoint{
			TokenURL: "https://online-go.com/oauth2/token/",
		},
	}

	previousToken := &oauth2.Token{
		RefreshToken: *refreshToken,
	}
	token, err := conf.TokenSource(ctx, previousToken).Token()
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println(token)

	client := conf.Client(ctx, token)

	resp, err := client.Get("http://online-go.com/api/v1/me/")
	if err != nil {
		fmt.Println(err)
		return
	}

	bodyBytes, _ := ioutil.ReadAll(resp.Body)
	fmt.Println(string(bodyBytes))
}
