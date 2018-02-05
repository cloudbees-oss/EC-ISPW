# EC-ISPW

Electric Flow integration for Compuware ISPW

# Procedures

## Create Assignment

Creates an Assignment.

## Load Task

Loads a Task for the specified Assignment.

## Get Assignment Information

Retrieves information about an Assignment.

## Get Assignment Task List

Retrieves the Task list for an Assignment.

## Get Assignment Task Information

Retrieves information about a Task in an Assignment.

## Generate Tasks in Assignment

Runs generate for the Tasks in Assignment.

## Deploy Assignment

Deploys an Assignment.

## Promote Assignment

Promotes an Assignment.

## Regress Assignment

Regresses an Assignment.

## Create Release

Creates a Release.

## Get Release Task List

Retrieves the Task list for a Release.

## Get Release Task Information

Retrieves information about a Task in a Release.

## Get Release Information

Retrieves information about a Release.

## Promote Release

Promotes a Release.

## Generate Tasks in Release

Runs generate for the Tasks in Release.

## Get Release Task Generate Listing

Retrieves a generate listing for a Task in a Release.

## Deploy Release

Deploys a Release.

## Regress Release

Regresses a Release.

## Get Set Information

Retrieves information about a Set.

## Get Set Task List

Retrieves the Task list for a Set.

## Get Set Deployment Information

Retrieves deployment information about a Set.

## Fallback Set

Fallbacks a Set from a previous deployment.



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
