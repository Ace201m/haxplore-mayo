# HaXplore - Submission Report

This is the official code repository for _MAYO_ . This _Android_ was developed during HaXplore, 
the on-campus event conducted by Codefest, the annual departmental fest of Computer Science department, IIT BHU Varanasi.

### _Mayo_

* _Deepansh Agrawal_
* _Mayank_
* _Khush Chopra_

#### Overview

_Each city face the problem of Immigration. There are some common probelms they face, Employment and the ditsance from government Services. This App basically makes it easy for immigrant worker to find jobs and apply for them. The App shows lots for job offers posted by the actual contractors of that area and worker can select the job he want. Other than this the App also gives some learning suggestions regarding the skills to learn according to the job request in that area in which they are immigrant. This can be a means for them through which they can make more money. One More tab for Services and benefits offered by the Goverment is provided which they can avail. The main aim is to spread the awareness of policies by Government through this section of Application._

#### Technology used
Android, AWS Relational Database Service, AWS EC2.

#### Screenshots/Demo Video

#### Usage

Android App for the Workers is TeamMayo for using this App the only option is to load the project into the Android Studio as some of the functionalities were not completed the APK is not generated.
Application is built to serve two kinds of users: Employee or Immigrant and Contractor.
Services our application look forward to serve:
 *  The first Point of Contact of our application for both types of users is their SignUp and LogIn. Each of user has to provide some   basic deatils to register themselves and whole through out the Application and Database, the persons Phone Number is used for their unique indentification.
 * After Login, Users of Employee category are provided with 5 broad functionalities.
 * MAIN - This Activity populates all the current Job Offers for the Employee. Job Offers are provided according the Skill Requirement   of Contractor and the skill set of the Employee.
  * APPLICATION - This Activity populates all the Job for which the employee has applied and the current status of Job with its other    details.
  * COURSE - This Activity lists the possibles skill which the user can learn. The Skills are listed after analysis of the job Request put by the contractors.
  * ACCOUNT - Here the User can update his details like address and city prefernce or the Skill in which he is looking to work upon.
  * GOV SERVICES - This aknowledge the user about the services and benefits hosted by goverment.
  * The Android App for the Contractor will contain three main functionalities, To LogIn/SignUp the Contractor, To populate his Job Requirement, To select the Application of Emplpoyee for that job. This contractor Application couldn't be completed.

The repo for REST APIs using **RDS** and **EC2** of AWS is [link](https://github.com/khushChopra/MayoAPI)

#### Tracks used

_**Influence the Mass**. 
Through this application we are aiming to solve the problem of immigrants. There is no present system available which solves the problem for immigrants. So we aim to provide a solution to their biggest of problems of Employment and the Govenrment Services they are not aware of. This comes under Influence the Mass as we have a target audience which is at the grass root level. With this the probelm is very common in today's developing cities of India._

#### AWS Services Used

_We have used **AWS Relational Database Service**. This service helped us to solve the challenges assosiated with the Database System Management. We have used this service alongside REST APIs. The usage of these services is very smooth and we have used it to populate tables are keep it updated with real time data. We have also used **Amazon's EC2 Service**. It has helped us in successful creation of the Host and provided us the environment to use the REST APIs._
