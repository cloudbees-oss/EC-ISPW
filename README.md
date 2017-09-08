# EC-ISPW

Electric Flow integration for Compuware ISPW

# Procedures

## Create Assignment

Creates an Assignment.

## Load Task

Loads a Task for the specified Assignment.

## Get Assignment Information

Retrieves Assignment information and saves it under the specified property.

## Get Assignment Task List

Retrieves Assignment Task List and saves it under the specified property.

## Get Assignment Task Information

Retrieves Assignment Task information and saved it under the specified property.

## Generate Tasks in Assignment

Generates Tasks in Assignment.

## Deploy Assignment

Deploys Assignment.

## Promote Assignment

Promotes Assignment.

## Create Release

Creates a Release.

## Get Release Information

Retrieves Release information and saves it under the specified property.

## Promote Release

Promotes Release.

## Deploy Release

Deploys Release.

## Regress Release

Regresses Release.

## Get Set Information

Gets Set information.

## Get Set Task List

Gets Set Task List.



# Building the plugin
1. Download or clone the EC-ISPW repository.

    ```
    git clone https://github.com/electric-cloud/EC-ISPW.git
    ```

5. Zip up the files to create the plugin zip file.

    ```
     cd EC-ISPW
     zip -r EC-ISPW.zip ./*
    ```

6. Import the plugin zip file into your ElectricFlow server and promote it.
