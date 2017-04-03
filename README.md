# _Library Catalog_

#### _Java Project for Epicodus - Week Four 2017_

#### By _**Grace Stuart (gstuart) and Chris Carr (coderknot)**_

## Description
> An application for librarians serving as a catalog to manage their books and patrons

## Setup/Installation Requirements
1. Install Java JRE (Runtime Enviornment).
2. Install Postgres.
3. Install Gradle.
4. Clone this repository onto your desktop. This will place the all files and folders in onto your computer.
5. Start Postgres in an additional terminal tab, and psql in yet another.
6. In psql tab enter `CREATE DATABASE library_catalog;`
7. In terminal tab enter `psql media < media.sql`
8. In psql tab enter `\c library_catalog` then `\dt`
9. In terminal tab enter `gradle run`
10. Navigate to 0.0.0.0:4567 in your internet browser

## Know Bugs
> No known bugs.

## Technology Used To Create Portfolio
* Java
* JUnit
* Gradle

## Specifications
|Behaviors|Input|Output|
|-----------|:-------:|:--------:|
|Application should create a book instance|-|true|
|Application should create a patron instance|-|true|
|Application should update book details|White Apple|White Apples|
|Application should update patron details|Chriss|Chris
|Application should delete a book|
|Application should delete a patron|
|Application should a patron should allow a patron to checkout a book|
|Application should assign a due date to a book|
|Application should assign a 3 book limit to the number of books checked out by an individual patron|
|Application should display a list of books checked out by a patron|
|Application should display a list of overdue books checked out by a patron|
|Application should display a list of all overdue books|
|Application should allow a patron to renew a book (Limit: renew 2 times)|
|Application should allow book search by author|
|Application should allow book search by title|

# Post MVP:
* As a librarian, I want to search for patrons by name, and see all the books they have out.
* As a patron, I want to see a history of all the books I checked out, so that I can look up the name of that awesome sci-fi novel I read three years ago.
* As a patron, I want to see a big reminder at the top of the page if I currently have an overdue book.
* As a patron, I want to receive an error message if I attempt to access the detail-view page for a book that no longer exists (ie: access a URL like localhost:4567/books/23, if the Book with an id of 23 has since been deleted.)
* As a patron, I want to receive an error message if I can't renew my book again, so I know it didn't work.


## Routes
|Behavior|Path|HTTP Verb|App.Java Example|
|-----------|:-------:|:--------:|:--------|
|Home page|/|GET|get("/", (request, response) -> { Map`<String, Object>` model = new HashMap`<String, Object>`(); model.put("template", "templates/index.vtl"); return new ModelAndView(model, layout); }, new VelocityTemplateEngine());|
|List all objects|/`persons`|GET|get("/`persons`", (request, response) -> { Map`<String, Object>` model = new HashMap`<String, Object>`(); model.put("`persons`", request.session().attribute("`persons`")); model.put("template", "templates/`persons`.vtl"); return new ModelAndView(model, layout); }, new VelocityTemplateEngine());|
|List all objects|/`monsters`|GET|get("/`monsters`", (request, response) -> { Map`<String, Object>` model = new HashMap`<String, Object>`(); model.put("`monsters`", request.session().attribute("`monsters`")); model.put("template", "templates/`monsters`.vtl"); return new ModelAndView(model, layout); }, new VelocityTemplateEngine());|
|Display specific object's details|/`persons`/:id|GET|get("/`persons`/:id", (request, respond) -> { Map`<String, Object>` model = new HashMap`<String, Object>`(); `Person` `person` = `Person`.find(Integer.parseInt(request.params(":id"))); model.put("`person`", `person`); model.put("template", "templates/`person`.vtl"); Return new ModelAndView(model, layout);}, new VelocityTemplateEngine());|
|Display specific object's details|/`monsters`/:id|GET|get("/`monsters`/:id", (request, respond) -> { Map`<String, Object>` model = new HashMap`<String, Object>`(); `Monsers` `monsters` =monsters `Monsers`.find(Integer.parseInt(request.params(":id"))); model.put("`monsters`", `monsters`); model.put("template", "templates/`monsters`.vtl"); Return new ModelAndView(model, layout);}, new VelocityTemplateEngine());|
|Display form|/`persons`/new|GET|get("`persons`/new", (request, response) -> { Map`<String, Object>` model = new HashMap`<String, Object>`(); model.put("template", "templates/`person`-form.vtl"); return new ModelAndView(model, layout); }, new VelocityTemplateEngine());|
|Display form|/`monsters`/new|GET|get("`monsters`/new", (request, response) -> { Map`<String, Object>` model = new HashMap`<String, Object>`(); model.put("template", "templates/`monster`-form.vtl"); return new ModelAndView(model, layout); }, new VelocityTemplateEngine());|
|Create new object when form is submitted|/`persons`|POST|post("/`persons`", (request, response) -> { Map`<String, Object>` model = new HashMap`<String, Object>`(); String name = request.queryParams("name"); `Person` `newPerson` = new `Person`(name);   model.put("template", "templates/`success`.vtl"); return new ModelAndView(model, layout); }, new VelocityTemplateEngine());|
|Create new object when form is submitted|/`monsters`|POST|post("/`monsters`", (request, response) -> { Map`<String, Object>` model = new HashMap`<String, Object>`(); String name = request.queryParams("name"); `Monster` `newMonster` = new `Monster`(name);   model.put("template", "templates/`success`.vtl"); return new ModelAndView(model, layout); }, new VelocityTemplateEngine());|



## License
*GPL*
Copyright (c) 2017 **_Grace Stuart and Chris Carr_**
