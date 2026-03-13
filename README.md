# Movie Recommender System

A simple movie recommendation application developed as a **team project**.  
The system suggests movies to users based on the **genres of movies they already like**.

The project was completed in two phases:
1. Building the recommendation system
2. Applying software testing techniques to validate the system

---

## Project Overview

The application reads two input files:

1. **Movies file**
2. **Users file**

Using this data, the system analyzes the genres of movies liked by each user and generates **movie recommendations**.

The output is saved in a third file containing recommended movies for each user.

---

## Example Input

### Movies File

Format:

Movie Name, Movie ID  
Genres

Example:

The Shawshank Redemption, TSR001  
Drama  

The Godfather, TG002  
Crime, Drama  

The Dark Knight, TDK003  
Action, Crime, Drama  

---

### Users File

Format:

User Name, User ID  
Liked Movie IDs

Example:

Hassan Ali, 12345678X  
TSR001, TDK003  

Ali Mohamed, 87654321W  
TG002  

---

## Example Output

Hassan Ali, 12345678X  
The Godfather  

Ali Mohamed, 87654321W  
The Shawshank Redemption, The Dark Knight  

---

## Recommendation Logic

1. Read movie information and genres
2. Read user liked movies
3. Identify the genres preferred by each user
4. Recommend movies that share similar genres but are not already liked by the user
5. Write recommendations to the output file

---

## Project Phases

### Phase 1 – System Implementation

Our team implemented the movie recommendation logic based on genre similarity.

Main components include:

- File parsing
- Genre matching
- Recommendation generation
- Output file creation

---

### Phase 2 – Software Testing

In the second phase, we applied different testing methodologies to ensure the system works correctly.

#### Testing Techniques Used

**Black Box Testing**
- Testing system functionality without considering internal implementation.

**White Box Testing**
- Testing internal logic and execution paths.

**Unit Testing**
- Verifying individual functions or modules.

**Integration Testing**
- Testing interactions between system components.

**Data Flow Testing**
- Ensuring correct definition and usage of variables throughout the program.

---

## Learning Outcomes

This project helped us gain practical experience in:

- Recommendation system design
- File-based data processing
- Software testing methodologies
- Test case design
- Collaborative software development

---

## Team

Developed as part of a team project.

(Add team member names here if desired)

---

## Author

John Emile
