# Mapbox Map

## Overview
* Integrated [mapbox map](https://www.mapbox.com/maps) in the Android mobile app using Kotlin.
* Implemented functionality to display various map styles (Street, Satellite, Outdoor).
* Handled user location permission & displayed user's current location on the map.
* User can add markers at desired locations on the map.

## Screenshots
<p>
  <img height="500" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617/46baf2b1-575c-4b7d-a2cd-59d6bed7576a"/>
  <img height="500" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617/6fc2ffa4-e453-4528-9322-bb070bd17a4f"/>
  <img height="500" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617/e8fc0a93-8b95-47b9-abcd-c75fac4862df"/>
  <img height="500" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617/9d333378-72bc-4e05-bd80-c0c68603d920"/>
  <img height="500" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617//2c677abd-53dd-4522-a112-2a04793f686a"/>
</p>

## Installation Steps
1. Signup at [mapbox.com](https://account.mapbox.com/auth/signup/).
2. Copy & save default public token from [tokens page](https://account.mapbox.com/access-tokens/).
3. Create a new secret token with `Downloads:Read` scope from [tokens page](https://account.mapbox.com/access-tokens/), and save it.
4. To keep the map tokens secure, store them in `«USER_HOME»/.gradle/gradle.properties` file.
   ```env
   MAPBOX_PUBLIC_TOKEN = your_mapbox_public_token
   MAPBOX_PRIVATE_TOKEN = your_mapbox_secret_token
   ```
5. Clone the repository.
   ```bash
   git clone https://github.com/Divyansh-Gemini/Mapbox-Map.git
   ```
6. Open the project in Android Studio.
7. Run the application.
