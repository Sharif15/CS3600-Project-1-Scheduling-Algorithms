# Welcome

## Team member
Sharif Islam <br>  
Christopher Hammer

## Getting started

To begin with the project start by making a clone of the repository.

```bash
# Cloning Repository
git clone git@github.com:Sharif15/CS3600-Project-1-Scheduling-Algorithms.git
# Stepping into the file
cd CS3600-Project-1-Scheduling-Algorithms
```

# Branching

Each team member should create their own branch before beginning development.

```bash
# Create your own branch
git checkout -b your-name

# Add your name to this README
git add .
git commit -m "Add team member branch"

# Push your branch to GitHub
git push -u origin your-name
```

For future work, switch to your branch with:

```bash
git checkout your-name
```

## Keeping Your Branch Up to Date

**Branches do not automatically receive changes from `main`.** Before starting work, update your branch with the latest changes from `main`.

First, make sure you are on your own branch:

```bash
git checkout your-name
```

Then fetch the latest changes and merge `main` into your branch:

```bash
git fetch origin
git merge origin/main
```

You should do this regularly, especially before starting a new task or pushing your work.

### If there are merge conflicts

Git may report a conflict if both your branch and `main` changed the same part of a file.

After resolving the conflicts:

```bash
git add .
git commit
```

Then continue working normally.

### Typical Workflow

```bash
# Start your work
git checkout your-name

# Get the latest changes from main
git fetch origin
git merge origin/main

# Make your changes
# ...

# Save your changes
git add .
git commit -m "Describe your changes"

# Push your branch
git push
```

**Important:** Do not work directly on `main`. Use your own branch for development and merge your completed work into `main` when it is ready.

# Running code 

We can use the makefile to build and run our data file:

```python
file_choice = [fcfs, sjf, pri, pri-rr, rr]

```
```bash
make your-file-choice
```

make sure to update your gitignore file with 
```.gitignore
*.class
```

you can clean up all your unneeded classes file 

```bash
make clean
```
