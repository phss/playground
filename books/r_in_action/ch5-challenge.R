options(digits=2)

Student <- c("John Davis", "Angela Williams", "Bullwinkle Moose",
             "David Jones", "Janice Markhammer", "Cheryl Cushing",
             "Reuven Ytzrhak", "Greg Knox", "Joel England",
             "Mary Rayburn")
Math <- c(502, 600, 412, 358, 495, 512, 410, 625, 573, 522)
Science <- c(95, 99, 80, 82, 75, 85, 80, 95, 89, 86)
English <- c(25, 22, 18, 15, 20, 28, 15, 30, 27, 18)
roster <- data.frame(Student, Math, Science, English,
                     stringsAsFactors=FALSE)

# perf scores
z <- scale(roster[, 2:4])
score <- apply(z, 1, mean)
roster <- cbind(roster, score)

# grades
y <- quantile(score, c(.8, .6, .4, .2))
roster <- within(roster, {
  grade <- NA
  grade[score >= y[1]] <- "A"
  grade[score < y[1] & score >= y[2]] <- "B"
  grade[score < y[2] & score >= y[3]] <- "C"
  grade[score < y[3] & score >= y[4]] <- "D"
  grade[score < y[4]] <- "F"
})

# adjust names
name <- strsplit((roster$Student), " ")
Firstname <- sapply(name, "[", 1)
Lastname <- sapply(name, "[", 2)
roster <- cbind(Firstname, Lastname, roster)

# sort
roster <- roster[order(Lastname, Firstname), ]