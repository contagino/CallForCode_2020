# contagiNO


Govt. authorities and public in general are too late to react to outbreak of a
disease on ground zero. By the time they react, it has already spread into
multiple locations
More location it spreads into, more difficult it becomes to contain the disease.
Key to prevention of Contagions: early detection of trend and quick
precautionary measures to prevent spreading
With popularity of social media, relevant information is often lost in deluge of
recreational posts and memes
Our solution proposes to mine relevant posts across social media platforms
(facebook, twitter, online news portal, etc.) and extract relevant information
to highlight initial trend for any disease in a location

## Installation and usage

first install node.js(preferred version 10). After a successfull install open comand prompt and install the latest ionic cli.
npm install -g @ionic/cli

Once it is completed, clone/download the application and then run install in root directory
npm install

Once installation is done, just run serve command to start the app locally.(http://localhost:8100)
ionic serve

## Build and Deployment

to build the application simply run build command.
ionic build
It will create build file within "www" folder. If you want you can copy the files and can serve the application in any server like tomcat,express etc.

To create an APK for android mobiles simply run 
ionic cordova build android
it will generate the apk file within platforms\android\app\build\outputs\apk\debug path. Copy the apk into an android mobile and install it manualyy. After successfull installation, open the app.
