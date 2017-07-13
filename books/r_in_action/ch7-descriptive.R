myvars <- c("mpg", "hp", "wt")

summary(mtcars[myvars])

aggregate(mtcars[myvars], by=list(am=mtcars$am), mean)