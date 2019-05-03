MasterSchedueler.Administrative - README
===

# MasterSchedueler.Administrative

## Table of Contents
1. [Overview](#Overview)
2. [Product Spec](#Product-Spec)
3. [Schema](#Schema)

## Overview
### Description
California State University Capstone Project. Salinas Union High School District Sponsored 
blah blah blah blah need to update this

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
  - [X] User can add students, teachers, classes, and sections
  - [X] User can update students, teachers, classes, and sections
  - [X] User can delete students, teachers, classes, and sections
  - [X] User can find student(s), teacher(s), class(es), and section(s)

**Optional Nice-to-have Stories**
  - [ ] User can download Schedule
  - [ ] User can upload .csv files to add students, teachers, classes, and sections

## Schema 

### Models
#### Student
|Property|Type|Description|
|---|---|---|
|id|String|Id of the student. Database Primary key|
|name|String|Name of student|
|grade|int|Grade of student|
|preferredClasses|List of String|List of classes student wants to take|
|preferred|List of Boolean|Maps to the classes student want to take and determines if a student has priority to take the class|
|academy|String|Student's academy they are part of [none, green, fast]|
|schedule|List of String|Student's schedule that was generated. A list of the class names|
|scheduleId|List of String|Student's schedule that was generated. A list of class Ids that map to class names|

#### Teacher
|Property|Type|Description|
|---|---|---|
|id|String|Id of the teacher. Database primary key|
|name|String|Name of the teacher|
|department|String|Department teacher is apart of|
|prep|int|Teacher's prep period|
|preferre_room|String|Room teacher wants to teach in|
|is80Percent|boolean|If true teacher teaches 5 sections. If false teacher teaches 4 sections.|
|academy|String|Teacher's academy they are part of [none, green, fast]|
|maxNumStudent|int|Maximum number of students the teacher can teach|
|currentNumStudent|int|Number of students teacher currently has|
|sections|List of Section|Teachers schedule|
|className|String|1rst class teacher can teach|
|className2|String|2nd class teacher can teach|
|className3|String|3rd class teacher can teach|

#### Class
|Property|Type|Description|
|---|---|---|
|id|String|Id of the Class. Database primary key|
|className|String|Name of the class|
|maxNumSections|int|Max number of sections that can be created form this class|
|maxNumStudentPerSection|int|Max number of students for each section|
|numStudentRegistered|int|how many students have registed for the class (in progress)|
    
#### Section extends Class
|Property|Type|Description|
|---|---|---|
|section_num|int|Section number. Section_num gets used with class id to create primary key|
|periodNum|int|Period when section will be held|
|roster|List of Par of string , string|List of student name and id that will take the class|
|teacherID|String|Teache ID who is teaching the class|
|room|String|how many students have registed for the class (in progress)|

### Networking
## List of network requests by screen
- Login 
    - (Read/Get) Query user credentials to get corresponding user
     ``` swift
     PFUser.logInWithUsername(inBackground: username, password: password)
            { (user, error) in
            if user != nil {
                self.performSegue(withIdentifier: "loginSegue", sender: nil)
            } else {
                print("Error: \(String(describing: error?.localizedDescription))")
            }
        }
    ```
- SignUp
    - (Create/Post) Create a new user
    ``` swift
        let user = PFUser()
        user.username = usernameField.text
        user.password = passwordField.text
        user.signUpInBackground { (success, error) in
            if success {
                self.performSegue(withIdentifier: "loginSegue", sender: nil)
            } else {
                print("Error: \(String(describing: error?.localizedDescription))")
            }
        }
    ```
- Create a Report
    - (Create/Post) Create a new report
    ``` swift
        let post = PFObject(className: "Posts")
        
        post["category"] = categoryField.text
        post["dirOfTravel"] = dirOfTravelField.text
        //post["ect"] = ect
        
        let imageData = imageView.image!.pngData()
        let file = PFFileObject(data: imageData!)

        post["image"] = file
        
        post.saveInBackground{ (success, error) in
            if success {
                self.dismiss(animated: true, completion: nil)
                print("Saved!")
            } else {
                print("error!")
            }
        }
    ```
- Get reports
    - (Read/Get)
    ``` swift
        let query = PFQuery(className:"Posts")
        //query.includeKeys(["author", "comments", "comments.author"])
        
        query.limit = amount
        
        query.findObjectsInBackground{ (posts, error) in
            if posts != nil {
                self.posts = posts!
                self.tableView.reloadData()
                self.myRefreshControl.endRefreshing()
            } else {
                print("Error: \(String(describing: error))")
            }
        }
    ```
- Get Profile
    - (Read/Get)
    ``` swift
        let query = PFQuery(className:"User")
        //query.includeKeys(["author", "comments", "comments.author"])
        
        query.limit = amount
        
        query.findObjectsInBackground{ (posts, error) in
            if posts != nil {
                self.posts = posts!
                self.tableView.reloadData()
                self.myRefreshControl.endRefreshing()
            } else {
                print("Error: \(String(describing: error))")
            }
        }
    ```
 - Selenium
     - Body structure
     ``` swift
         
      Form: Codable
      var issueCategory: String
      var dirOfTravel: String
      var transMode: String
      var nearestCrossStreet: String
      var dateTime: String
      var latitude: String
      var longitude: String
      var descripText: String
      var name: String
      var email: String
      var phone: String
      var followUp: Bool
    ```
    - APi CAll
    ``` swift
        @IBAction func postApiCall(_ sender: Any) {
        
        let url = URL(string: "http://localhost:8089/submitPost")
        
        // Specify this request as being a POST method
        var request = URLRequest(url: url!)
        request.httpMethod = "POST"
        // Make sure that we include headers specifying that our request's HTTP body
        // will be JSON encoded
        var headers = request.allHTTPHeaderFields ?? [:]
        headers["Content-Type"] = "application/json"
        request.allHTTPHeaderFields = headers
        
        //let post = Response(status: "daniel sampson", error: false)
        let post = Form() // create your own form
        
        let encoder = JSONEncoder()
        do {
            let jsonData = try encoder.encode(post)
            // ... and set our request's HTTP body
            request.httpBody = jsonData
            //print("jsonData: ", String(data: request.httpBody!, encoding: .utf8) ?? "no body data")
        } catch {
            print(error.localizedDescription)
        }
        
        // Create and run a URLSession data task with our JSON encoded POST request
        let config = URLSessionConfiguration.default
        let session = URLSession(configuration: config)
        let task = session.dataTask(with: request) { data, response, error in
            if let error = error {
                print(error.localizedDescription)
            } else if let data = data {
                let msg = try! JSONSerialization.jsonObject(with: data, options:[]) as! [String: Any]
                print(msg)

                print(msg["status"] as! String)
            }
        }
        task.resume()
        
    }
    ```
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
