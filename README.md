# Mapbox Map

## Overview
* Integrated [mapbox map](https://www.mapbox.com/maps) in the Android mobile app using Kotlin.
* Implemented functionality to display various map styles (Street, Satellite, Outdoor).
* Handled user location permission & displayed user's current location on the map.
* User can add markers at desired locations on the map.

## Tech Stack
- Kotlin
- XML
- Android Studio

<p align="center">
    <a href="https://www.divyanshgemini.dev/">
        <img src="https://skillicons.dev/icons?i=androidstudio,kotlin" alt="tech stack" />
    </a>
</p>

## Screenshots
<p align="center">
  <img height="300" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617/b297a2b8-583c-4e6a-94c2-f3e01a137341"/>
  <img height="300" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617/8db35a36-f46f-4ae0-8278-3d95feda603f"/>
  <img height="300" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617/7a5f1074-3d90-47aa-b7db-da857ba9ffe8"/>
  <img height="300" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617/9ff06dfb-0cf6-4363-95f3-ae292c61966d"/>
  <img height="300" src="https://github.com/Divyansh-Gemini/Mapbox-Map/assets/88696617/1e7f74ac-bb0f-4ec5-91ff-803d8ef35bee"/>
</p>


## Installation Steps
1. Signup at [mapbox.com](https://account.mapbox.com/auth/signup/).
2. Copy & save default public token from [tokens page](https://account.mapbox.com/access-tokens/).
3. Create a new secret token with `Downloads:Read` scope from [tokens page](https://account.mapbox.com/access-tokens/), and save it.
4. To keep the map tokens secure, store them in `«USER_HOME»/.gradle/gradle.properties` file.
   ```
   MAPBOX_PUBLIC_TOKEN = your_mapbox_public_token
   MAPBOX_PRIVATE_TOKEN = your_mapbox_secret_token
   ```
5. Clone the repository.
   ```bash
   git clone https://github.com/Divyansh-Gemini/Mapbox-Map.git
   ```
6. Open the project in Android Studio.
7. Run the application.
