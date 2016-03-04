# Band Venue App

#### Creates an app that allows adding bands and venues, and creating linked lists between the two, February 26, 2016.

#### By Ryan King

## Description

This was created as an Epicodus exercise in Behavior Driven Development, web app development with Spark and Velocity, and solo programming.

## Setup/Installation Requirements

* Clone this repository.
* Make sure you have Gradle and Java installed.
* Start Psql and Postgres
* in PSQL:
* CREATE DATABASE bands_venues;
* Connect to database by using \c command: \c hair_salon;
* CREATE TABLE bands (id serial PRIMARY KEY, name varchar);
* CREATE TABLE venues (id serial PRIMARY KEY, name varchar);
* CREATE TABLE bands_venues (id serial PRIMARY KEY, bandid int, venueid int);
* In the top level of the cloned directory, run the following command in your terminal:

`gradle run`

* Open your web browser of choice to localhost:4567

## Technologies Used

Java, Spark, Junit, Velocity, Bootstrap, Postgres, Psql

### License

This software is licensed under the MIT license.

Copyright (c) 2016 Ryan King

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
