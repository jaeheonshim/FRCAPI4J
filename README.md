# FRCAPI4J

FRCAPI4J is a Java library for interfacing with the [FIRST Events API](https://frc-events.firstinspires.org/services/API).

## Installation

Use the maven package to install FRCAPI4J

```
<dependency>
  <groupId>com.jaeheonshim</groupId>
  <artifactId>frcapi4j</artifactId>
  <version>0.1.0</version>
</dependency>
```

Alternatively, download the [jarfile from the releases](https://github.com/jaeheonshim/FRCAPI4J/releases) and add that to your classpath manually.

## Usage
**Note: You'll need to register for access to the FIRST API in order to use this library. If you don't have API credentials yet, you can obtain them here: https://frc-events.firstinspires.org/services/API/register**

This library is designed to model the FIRST API as closely as possible. For instance, accessor method names correspond with the names of the response parameter keys from the data is received from the API.

An instance of the `com.jaeheonshim.frcapi4j.FRCAPI` class must be created to access the API.
```java
com.jaeheonshim.frcapi4j.FRCAPI api = new com.jaeheonshim.frcapi4j.FRCAPI("username", "password");
```

Replace "username" and "password" with the credentials you received from FIRST when registering for their API. If you don't have these credentials, you need to obtain them by [registering for API access](https://frc-events.firstinspires.org/services/API/register).

Optionally, you can provide the current season as a third parameter for the constructor. This will allow you to omit the season when you are accessing the api.

```java
com.jaeheonshim.frcapi4j.FRCAPI api = new com.jaeheonshim.frcapi4j.FRCAPI("username", "password", 2020);
```

You can access the official documentation for the FIRST events API [here](https://frcevents2.docs.apiary.io/).  
The full documentation for this library is available here: [documentation](documentation.md)

### Making a request
First, find the method in the com.jaeheonshim.frcapi4j.FRCAPI class that corresponds with the endpoint of the FIRST API you'd like to make a request to. For example, let's say that we wanted to find the team listing for the 2020 season.

For responses that have multiple pages of information, a class is dedicated to that response under the com.jaeheonshim.firstapi4j.com.jaeheonshim.frcapi4j.structures.listings package. You can specify the page you want from the method.

For requests where some parameters are optional, you can set those parameters in the method as null. Parameters that are required are marked with the @NotNull annotation.

```java
com.jaeheonshim.frcapi4j.FRCAPI api = new com.jaeheonshim.frcapi4j.FRCAPI("username", "password");
TeamListings listings = api.getTeamListings(2020, null, null, null);
List<Team> teams = listings.getTeams();

for(Team team : teams) {
    System.out.println(team.getNameShort());
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.