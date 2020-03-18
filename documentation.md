# Documentation
The methods below are all found on instances of the FRCAPI class and are used to access the First Events API.

## `public List<Alliance> getEventAlliances(int season, @NotNull String eventCode)`

Returns details about alliance selection at a particular event in a particular season

 * **Parameters:**
   * `season` — **Required** Numeric year of the event from which the event alliances are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the alliance selection results are requested. Must be at least 3 characters.
 * **Returns:** List containing alliances from api.

## `public List<Alliance> getEventAlliances(@NotNull String eventCode)`

Returns details about alliance selection at a particular event in a particular season

 * **Parameters:** `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the alliance selection results are requested. Must be at least 3 characters.
 * **Returns:** List containing alliances from api. Returns null if season not specified in FRCAPI object.

## `public List<Award> getEventAwards(int season, @NotNull String eventCode, Integer teamNumber)`

Returns details about awards presented at a particular event in a particular season.

 * **Parameters:**
   * `season` — **Required** Numeric year of the event from which the award listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `eventCode` — **Optional** Optional case insensitive alphanumeric eventCode of the event from which the awards are requested. Must be at least 3 characters.
   * `teamNumber` — **Optional** Optional teamNumber to search for within the results.
 * **Returns:** List containing awards from api.

## `public List<Award> getEventAwards(String eventCode, Integer teamNumber)`

Returns details about awards presented at a particular event in a particular season.

 * **Parameters:**
   * `eventCode` — **Optional** Optional case insensitive alphanumeric eventCode of the event from which the awards are requested. Must be at least 3 characters.
   * `teamNumber` — **Optional** Optional teamNumber to search for within the results.
 * **Returns:** List containing awards from api. Returns null if season not specified in FRCAPI object.

## `public List<MatchResult> getMatchResults(int season, @NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer matchNumber, Integer start, Integer end)`

Returns the match results for all matches of a particular event in a particular season.

 * **Parameters:**
   * `season` — **Required** Numeric year of the event from which the match results are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the results are requested. Must be at least 3 characters.
   * `tournamentLevel` — **Optional** Optional tournamentLevel of desired match results.
   * `teamNumber` — **Optional** Optional teamNumber to search for within the results. Only returns match results in which the requested team was a participant.
   * `matchNumber` — **Optional** Optional specific single matchNumber of result.
   * `start` — **Optional** Optional start match number for subset of results to return.
   * `end` — **Optional** Optional end match number for subset of results to return (inclusive).
 * **Returns:** List containing MatchResults from api.

## `public List<MatchResult> getMatchResults(@NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer matchNumber, Integer start, Integer end)`

Returns the match results for all matches of a particular event in a particular season.

 * **Parameters:**
   * `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the results are requested. Must be at least 3 characters.
   * `tournamentLevel` — **Optional** Optional tournamentLevel of desired match results.
   * `teamNumber` — **Optional** Optional teamNumber to search for within the results. Only returns match results in which the requested team was a participant.
   * `matchNumber` — **Optional** Optional specific single matchNumber of result.
   * `start` — **Optional** Optional start match number for subset of results to return.
   * `end` — **Optional** Optional end match number for subset of results to return (inclusive).
 * **Returns:** List containing MatchResults from api. Returns null if season not specified in FRCAPI object.

## `public List<Ranking> getEventRankings(int season, @NotNull String eventCode, Integer teamNumber, Integer top)`

Returns team ranking detail from a particular event in a particular season.

 * **Parameters:**
   * `season` — **Required** Numeric year of the event from which the rankings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the rankings are requested. Must be at least 3 characters.
   * `teamNumber` — **Optional** Optional team number of the team whose ranking is requested.
   * `top` — **Optional** Optional number of requested top ranked teams to return in result.
 * **Returns:** List containing Rankings from api.

## `public List<Ranking> getEventRankings(@NotNull String eventCode, Integer teamNumber, Integer top)`

Returns team ranking detail from a particular event in a particular season.

 * **Parameters:**
   * `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the rankings are requested. Must be at least 3 characters.
   * `teamNumber` — **Optional** Optional team number of the team whose ranking is requested.
   * `top` — **Optional** Optional number of requested top ranked teams to return in result.
 * **Returns:** List containing Rankings from api. Returns null if season not specified in FRCAPI object.

## `public DistrictRankings getDistrictRankings(int season, @NotNull String districtCode, Integer teamNumber, Integer top, Integer page)`

Returns team ranking detail from a particular team in a particular season

 * **Parameters:**
   * `season` — **Required** Numeric year of the event from which the rankings are requested. Must be 4 digits and greater than or equal to 2017, and less than or equal to the current year.
   * `districtCode` — **Required** Case insensitive alphanumeric districtCode of the district from which the rankings are requested. Must be at least 2 characters.
   * `teamNumber` — **Optional** Optional team number of the team whose ranking is requested.
   * `top` — **Optional** Optional number of requested top ranked teams to return in result.
   * `page` — **Optional** Numeric page of results to return. If not included, page 1 will be returned.
 * **Returns:** DistrictRankings object from api.

## `public DistrictRankings getDistrictRankings(@NotNull String districtCode, Integer teamNumber, Integer top, Integer page)`

Returns team ranking detail from a particular team in a particular season

 * **Parameters:**
   * `districtCode` — **Required** Case insensitive alphanumeric districtCode of the district from which the rankings are requested. Must be at least 2 characters.
   * `teamNumber` — **Optional** Optional team number of the team whose ranking is requested.
   * `top` — **Optional** Optional number of requested top ranked teams to return in result.
   * `page` — **Optional** Numeric page of results to return. If not included, page 1 will be returned.
 * **Returns:** DistrictRankings object from api. Returns null if season not specified in FRCAPI object.

## `public List<ScheduledMatch> getEventSchedule(int season, @NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer start, Integer end)`

Returns the match schedule for the desired tournament level of a particular event in a particular season.

 * **Parameters:**
   * `season` — **Required** Numeric year of the event from which the schedule is requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the schedule are requested. Must be at least 3 characters.
   * `tournamentLevel` — **Required** tournamentLevel of desired match schedule.
   * `teamNumber` — **Optional** Optional teamNumber to search for within the schedule. Only returns matches in which the requested team participated.
   * `start` — **Optional** Optional start match number for subset of results to return (inclusive).
   * `end` — **Optional** Optional end match number for subset of results to return (inclusive).
 * **Returns:** List containing ScheduledMatches from api.

## `public List<ScheduledMatch> getEventSchedule(@NotNull String eventCode, TournamentLevel tournamentLevel, Integer teamNumber, Integer start, Integer end)`

Returns the match schedule for the desired tournament level of a particular event in a particular season.

 * **Parameters:**
   * `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the schedule are requested. Must be at least 3 characters.
   * `tournamentLevel` — **Required** tournamentLevel of desired match schedule.
   * `teamNumber` — **Optional** Optional teamNumber to search for within the schedule. Only returns matches in which the requested team participated.
   * `start` — **Optional** Optional start match number for subset of results to return (inclusive).
   * `end` — **Optional** Optional end match number for subset of results to return (inclusive).
 * **Returns:** List containing ScheduledMatches from api. Returns null if season not specified in FRCAPI object.

## `public List<HybridSchedule> getHybridSchedule(int season, @NotNull String eventCode, @NotNull TournamentLevel tournamentLevel, Integer start, Integer end)`

Returns the match schedule for the desired tournament level of a particular event in a particular season in the hybrid format

 * **Parameters:**
   * `season` — **Required** Numeric year of the event from which the hybrid schedule is requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the hybrid schedule are requested. Must be at least 3 characters.
   * `tournamentLevel` — **Required** tournamentLevel of desired hybrid schedule.
   * `start` — **Optional** Optional start match number for subset of results to return (inclusive).
   * `end` — **Optional** Optional end match number for subset of results to return (inclusive).
 * **Returns:** List containing HybridSchedules from api.

## `public List<HybridSchedule> getHybridSchedule(@NotNull String eventCode, @NotNull TournamentLevel tournamentLevel, Integer start, Integer end)`

Returns the match schedule for the desired tournament level of a particular event in a particular season in the hybrid format

 * **Parameters:**
   * `eventCode` — **Required** Case insensitive alphanumeric eventCode of the event from which the hybrid schedule are requested. Must be at least 3 characters.
   * `tournamentLevel` — **Required** tournamentLevel of desired hybrid schedule.
   * `start` — **Optional** Optional start match number for subset of results to return (inclusive).
   * `end` — **Optional** Optional end match number for subset of results to return (inclusive).
 * **Returns:** List containing HybridSchedules from api. Returns null if season not specified in FRCAPI object.

## `public Season getSeason(int season)`

Returns a high level glance of a particular FRC season

 * **Parameters:** `season` — **Required** Numeric year of the event from which the season summary is requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
 * **Returns:** Season object containing information about the requested FRC season.

## `public Season getSeason()`

Returns a high level glance of a particular FRC season

 * **Returns:** Season object containing information about the requested FRC season. Returns null if season not specified in FRCAPI object. Returns null if season not specified in FRCAPI object.

## `public List<Event> getEvents(int season, String eventCode, Integer teamNumber, String districtCode, Boolean excludeDistrict)`

Returns all FRC official district and regional events in a particular season

 * **Parameters:**
   * `season` — **Required** Numeric year from which the event listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `eventCode` — **Optional** Case insensitive alphanumeric eventCode of the event about which details are requested.
   * `teamNumber` — **Optional** Numeric teamNumber of the team from which the attending event listings are requested.
   * `districtCode` — **Optional** Case insensitive districtCode of the district from which event listings are requested.
   * `excludeDistrict` — **Optional** Boolean to specify whether or not to exclude district events in the event listings. true means exclude, but if no value is specified, false will be used (include district events). Excluding district events also excludes district championships.
 * **Returns:** List containing Events from api.

## `public List<Event> getEvents(String eventCode, Integer teamNumber, String districtCode, Boolean excludeDistrict)`

Returns all FRC official district and regional events in a particular season

 * **Parameters:**
   * `eventCode` — **Optional** Case insensitive alphanumeric eventCode of the event about which details are requested.
   * `teamNumber` — **Optional** Numeric teamNumber of the team from which the attending event listings are requested.
   * `districtCode` — **Optional** Case insensitive districtCode of the district from which event listings are requested.
   * `excludeDistrict` — **Optional** Boolean to specify whether or not to exclude district events in the event listings. true means exclude, but if no value is specified, false will be used (include district events). Excluding district events also excludes district championships.
 * **Returns:** List containing Events from api. Returns null if season not specified in FRCAPI object.

## `public List<District> getDistricts(int season)`

Returns all FRC official districts of a particular season.

 * **Parameters:** `season` — **Required** Numeric year from which the district listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
 * **Returns:** List containing Districts from api.

## `public List<District> getDistricts()`

Returns all FRC official districts of a particular season.

 * **Returns:** List containing Districts from api. Returns null if season not specified in FRCAPI object.

## `public TeamListings getTeamListings(int season, Integer teamNumber, String eventCode, String districtCode, Integer page)`

Returns all FRC official teams in a particular season

 * **Parameters:**
   * `season` — **Required** Numeric year from which the team listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `teamNumber` — **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
   * `eventCode` — **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
   * `districtCode` — **Optional** Case insensitive districtCode code of the district from which team listings are requested.
   * `page` — **Optional** Numeric page of results to return. If not included, page 1 will be returned.
 * **Returns:** TeamListings object containing teams requested from api.

## `public TeamListings getTeamListings(Integer teamNumber, String eventCode, String districtCode, Integer page)`

Returns all FRC official teams in a particular season

 * **Parameters:**
   * `teamNumber` — **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
   * `eventCode` — **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
   * `districtCode` — **Optional** Case insensitive districtCode code of the district from which team listings are requested.
   * `page` — **Optional** Numeric page of results to return. If not included, page 1 will be returned.
 * **Returns:** TeamListings object containing teams requested from api. Returns null if season not specified in FRCAPI object.

## `public List<Registration> getRegistrations(int season, Integer teamNumber, String eventCode)`

Returns all registrations of teams in a particular season at particular events.

 * **Parameters:**
   * `season` — **Required** Numeric year from which the team listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `teamNumber` — **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
   * `eventCode` — **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
 * **Returns:** Returns list containing Registrations from api.

## `public List<Registration> getRegistrations(Integer teamNumber, String eventCode)`

Returns all registrations of teams in a particular season at particular events.

 * **Parameters:**
   * `teamNumber` — **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
   * `eventCode` — **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
 * **Returns:** Returns list containing Registrations from api. Returns null if season not specified in FRCAPI object

## `public TeamAvatarListings getAvatarListings(int season, Integer teamNumber, String eventCode, Integer page)`

This endpoint applies only to the 2018 or later seasons. Requests for other seasons will result in a Bad Season error. The team avatar listings API returns all FRC official teams in a particular season with, if applicable, their Avatar.

 * **Parameters:**
   * `season` — **Required** Numeric year from which the team listings are requested. Must be 4 digits and greater than or equal to 2015, and less than or equal to the current year.
   * `teamNumber` — **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
   * `eventCode` — **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
   * `page` — **Optional** Numeric page of results to return. If not included, page 1 will be returned.
 * **Returns:** Returns TeamAvatarListings containing Team Avatars requested from api.

## `public TeamAvatarListings getAvatarListings(Integer teamNumber, String eventCode, Integer page)`

This endpoint applies only to the 2018 or later seasons. Requests for other seasons will result in a Bad Season error. The team avatar listings API returns all FRC official teams in a particular season with, if applicable, their Avatar.

 * **Parameters:**
   * `teamNumber` — **Optional** Numeric teamNumber of the team about which information is requested. Must be 1 to 4 digits.
   * `eventCode` — **Optional** Case insensitive alphanumeric eventCode of the event from which details are requested.
   * `page` — **Optional** Numeric page of results to return. If not included, page 1 will be returned.
 * **Returns:** Returns TeamAvatarListings containing Team Avatars requested from api. Returns null if season not specified in FRCAPI object
