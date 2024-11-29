Android Developer Interview Test

Objective

This project implements the following tasks based on the provided interview requirements:
1.	UI Design: Build the user interface based on the Figma design.
2.	API Integration: Use the provided API to create and view a trip.
3.	Submission: Package the project as a GitHub repository with a working APK.

Features

	•	UI: Replicated the design provided in the Figma link.
	•	Trip Management:
	•	Create a trip.
	•	View trips using the API provided.

Requirements

Prerequisites

	•	Android Studio: Giraffe (or later) with Kotlin support.
	•	API Credentials: Provided by the test (You may need to request new credentials if you exceed the API limits).
	•	Internet Access: Required for API interactions.

Setup Instructions

	1.	Clone the Repository:

git clone <repository-link>

	2.	Open the Project in Android Studio.
	3.	API Integration:
	•	The API endpoints for CRUD operations are:
Create a Trip (POST)
Base URL:

https://echo.free.beeceptor.com

This endpoint echoes the payload back as a response.
View Trips (GET)
Base URL:

https://gopaddiapi.free.beeceptor.com

	•	Update any necessary credentials in the project file (e.g Base url in ApiClientUtil.kt or EchoApiClientUtil.kt).

	4.	Build and Run the Project:
	•	Select a device/emulator and run the app from Android Studio.

API Endpoints

	1.	Create a Trip:
POST https://echo.free.beeceptor.com/trip
(This endpoint echoes the payload back as a response)
Payload and Response Example:

{
"id": "123",
"tripName": "Summer Vacation",
"tripDescription": "A fun trip to the beach.",
"location": "Miami, USA",
"travelStyle": "Family",
"startDate": "Jan 1, 2024",
"endDate": "Jan 7, 2024",
"duration": "7 days",
"imageUrl": "https://example.com/image.jpg"
}


	2.	View Trips:
GET https://gopaddiapi.free.beeceptor.com/trips
Response Example:

[
{
"id": "1",
"tripName": "Paris Getaway",
"tripDescription": "A romantic escape to the City of Lights.",
"location": "Paris, France",
"travelStyle": "Couple",
"startDate": "Jan 15, 2024",
"endDate": "Jan 22, 2024",
"duration": "7 days",
"imageUrl": "https://images.unsplash.com/photo-1522093007474-d86e9bf7ba6f?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cGFyaXN8ZW58MHx8MHx8fDA%3D"
},
{
"id": "2",
"tripName": "New York Adventure",
"tripDescription": "Explore the vibrant life of New York City.",
"location": "New York City, United States",
"travelStyle": "Group",
"startDate": "Feb 10, 2024",
"endDate": "Feb 14, 2024",
"duration": "4 days",
"imageUrl": "https://images.unsplash.com/photo-1602940659805-770d1b3b9911?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fE5ldyUyMFlvcmslMjBDaXR5fGVufDB8fDB8fHww"
}
]



Project Structure

	•	app/src/main/java: Contains the main application logic and ViewModel.
	•	app/src/main/res: Contains resources such as layouts, drawables, and strings.
	•	README.md: This documentation file.
	•	build.gradle: Project dependencies and configuration.

APK Download

Download the APK file from the following link:
https://drive.google.com/file/d/1GPUhh00ibYMhYuJ9ReYS2NJHk6iVkYDx/view?usp=share_link

Notes

	•	All UI elements follow the Figma design closely, including spacing, fonts, and colors.
	•	The app gracefully handles API errors and provides user feedback.
	•	The API is integrated for both creating and viewing trips.
	•	Error messages are displayed if the API request fails, providing feedback to the user.