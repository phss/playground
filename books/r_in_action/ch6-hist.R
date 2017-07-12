hist(mtcars$mpg)

hist(mtcars$mpg,
     breaks=12,
     col="red",
     xlab="Miles Per Gallon",
     main="Colored histogram with 12 bars")

hist(mtcars$mpg,
     freq = FALSE,
     breaks=12,
     col="red",
     xlab="Miles Per Gallon",
     main="Colored histogram with 12 bars")
rug(jitter(mtcars$mpg))
lines(density(mtcars$mpg), col="blue", lwd=2)