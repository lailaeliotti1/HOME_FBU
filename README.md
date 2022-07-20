Original App Design Project - README Template
===

# HOME

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
This app is a way for users to search for new homes! It will use the AttomData, Zipcode, and Google Streetview Static API to gather data and upload houses on to the app. Users will be able to sign in, choose preferences, such as location, number of rooms, and property type.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** "Lifestyle"
- **Mobile:** "This app is mobile due to the ability to log in, and users can click on houses instantly."
- **Story:** "This app is for that new college grad, couple who is ready to take the next step, or for the person who wants their own space. This app will allow them to find their new sanctuary of peace, and a place to call HOME."
- **Market:** "Anyone who is financially ready to purchase their next home."
- **Habit:**"Users will be rewarded with discounts on homes after multiple consecutive days of using the app."
- **Scope:**"V1 would allow user to sign in/ create a new account. V2 would allow users to add preferences."

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* [fill in your required user stories here]
* ...
* User can create an account
* User can login
* User can enter preferences
* User can scroll through real estate, and view pictures
* User can logout

**Optional Nice-to-have Stories**

* [fill in your required user stories here]
* User can view closest houses to them or close in compability by recommendation.

### 2. Screen Archetypes

* Create Account
   * User can create an account
* Login 
    * User can sign in
* Save preferences
   * User can input house preferences and save them.
* Stream
    * User can view possible homes
* Logout
    * User can logout

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Saved preferences
* Home Stream

**Flow Navigation** (Screen to Screen)

* Registration
   * => Home
* Login
   * => Home
* Saved preferences
    * => Home stream
* Home stream
    * => Home details(where you can save houses)
* Logout
    * => Login

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="https://user-images.githubusercontent.com/93938274/174900993-6294f53e-b067-4af5-a96f-b2ea7277b319.jpeg"
 width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
### Models
#### User

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user (default field) |
   | userName      | String   | Username for account   |
   | password      | String   | password for account   |
   | email         | String   | email for account      |
##### UserPreferences
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | Zipcode       | String   | location preference for user |
   | Zipcode       | String   | location preference for user |
   | noOfBedrooms  | Numbers  | Number of bedrooms preference for user |
   | noOfBathrooms | Numbers  | Number of bathrooms preference for user |
   | MaxBed     | Numbers  | Number of bathrooms preference for user + 1|
   | propertyType  | String   | House style preference for user |
   | recommendations|Boolean   | User checks off if they want preferences |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
   
   
#### Home

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the property (default field) |
   | address       | String   | Property address |
   | homeNoOfBedrooms| Number | number of rooms the house has |
   | homeNoOfBathrooms| Number| number of bathrooms the house has |
   | yearBuilt     | Number   | Year the house was built |
   | Latitude      | Number  | Latitude of house |
   | Longitude     | Number  | Longitude of house |
   | ImageUrl      | String  | ImageUrl of house |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
   
 
   
   
### Networking
#### List of network requests by screen
   - Home Feed Screen
      - (Read/GET) Query all posts where user is author
         ```swift
         let query = PFQuery(className:"Post")
         query.whereKey("author", equalTo: currentUser)
         query.order(byDescending: "createdAt")
         query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
            if let error = error { 
               print(error.localizedDescription)
            } else if let posts = posts {
               print("Successfully retrieved \(posts.count) posts.")
           // TODO: Do something with posts...
            }
         }
         ```
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
      - (Create/POST) Create a new comment on a post
      - (Delete) Delete existing comment
   - Create Post Screen
      - (Create/POST) Create a new post object
   - Profile Screen
      - (Read/GET) Query logged in user object
      - (Update/PUT) Update user profile image
#### [OPTIONAL:] Existing API Endpoints
##### An API Of Ice And Fire
- Base URL - [http://www.anapioficeandfire.com/api](http://www.anapioficeandfire.com/api)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /characters | get all characters
    `GET`    | /characters/?name=name | return specific character by name
    `GET`    | /houses   | get all houses
    `GET`    | /houses/?name=name | return specific house by name

##### Game of Thrones API
- Base URL - [https://api.got.show/api](https://api.got.show/api)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /cities | gets all cities
    `GET`    | /cities/byId/:id | gets specific city by :id
    `GET`    | /continents | gets all continents
    `GET`    | /continents/byId/:id | gets specific continent by :id
    `GET`    | /regions | gets all regions
    `GET`    | /regions/byId/:id | gets specific region by :id
    `GET`    | /characters/paths/:name | gets a character's path with a given name
