# FRCAPI4J

FRCAPI4J is a Java library for interfacing with the [FIRST Events API](https://frc-events.firstinspires.org/services/API).

## Installation

Use the maven package to install FRCAPI4J

```
<dependency>
  <groupId>com.jaeheonshim</groupId>
  <artifactId>FIRSTAPI4J</artifactId>
  <version>0.1.0</version>
</dependency> 
```

Alternatively, download the [jarfile](https://pkg.githubusercontent.com/246727341/e081f900-6917-11ea-9ab3-cbe1e8a41c0f?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20200318%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20200318T165946Z&X-Amz-Expires=300&X-Amz-Signature=4b6bd50bdc9350f2fd35b57345cf4dfb62dbd750628dd65caeb605473b0ed2f3&X-Amz-SignedHeaders=host&actor_id=35268332&response-content-disposition=filename%3DFIRSTAPI4J-0.1.0.jar&response-content-type=application%2Foctet-stream) and add that to your classpath manually.

## Usage

This library is designed to model the FIRST API as closely as possible. For instance, accessor method names correspond with the names of the response parameter keys from the data is received from the API.

An instance of the `FRCAPI` class must be created to access the API.
```java
FRCAPI api = new FRCAPI("username", "password");
```

Replace "username" and "password" with the credentials you received from FIRST when registering for their API. If you don't have these credentials, you need to obtain them by [registering for API access](https://frc-events.firstinspires.org/services/API/register).

Optionally, you can provide the current season as a third parameter for the constructor. This will allow you to omit the season when you are accessing the api.

```java
FRCAPI api = new FRCAPI("username", "password", 2020);
```

You can access the official documentation for the FIRST events API [here](https://frcevents2.docs.apiary.io/).

### Making a request
First, find the method in the FRCAPI class that corresponds with the endpoint of the FIRST API you'd like to make a request to. For example, let's say that we wanted to find the team listing for the 2020 season.

For responses that have multiple pages of information, a class is dedicated to that response under the com.jaeheonshim.firstapi4j.structures.listings package. You can specify the page you want from the method.

For requests where some parameters are optional, you can set those parameters in the method as null. Parameters that are required are marked with the @NotNull annotation.

```java
FRCAPI api = new FRCAPI("username", "password");
TeamListings listings = api.getTeamListings(2020, null, null, null);
List<Team> teams = listings.getTeams();

for(Team team : teams) {
    System.out.println(team.getNameShort());
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.