# Cloud Based Blood Bank System
The software system is an online blood bank management system that helps in managing
various blood bank operations effectively. The project consists of a central repository
containing various blood deposits available along with associated details hosted on windows
based cloud server. These details include blood type, storage area and date of storage. These
details help in maintaining and monitoring the blood deposits. The project is an online system
that allows to check whether required blood deposits of a particular group are available in the
blood bank efficiently using cloud server. Moreover the system also has added features such
as patient name and contacts, blood booking and even need for certain blood group is posted
on the application to find available donors for a blood emergency.

## Tools Used
* Figma - Designing the user interface
* Android Studio - To create the android application
* Firebase by Google - Cloud Services: Storage, Authentication

## Procedure
* To run the app build the project using android studio
* The app uses firebase database to store the users, blood details and FAQs
* When a user submits a donor details card, it is instantly updated in the database with a status flag
  * P stands for pending, Initially all the donors have a P status. When the admin changes it to A, all the app users can see that donor on their phones in realtime.
* FAQs can be added by the donor without the need of updating the app in addition, notifications can also be sent.
* To successfully build the app, you need to add the google-services.json file which can be downloaded from your firebase console. If you're a part of the project team, ping me for the file.
