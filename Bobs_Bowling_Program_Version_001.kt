//Bob's Bowling Program - version 1.000
//This Version is awkward but it works for a number of data sets or game inputs

// This program is a collection of useful examples for Kotlin programming. The program will run
// within the IntelliJ IDEA IDE(Integration Development Environment). This code is written on IntelliJ IDEA Community 2020.1
// Runtime version: 11.0.6+8-b765.40 amd64 and will run on other similar versions as is.
// Make an empty Kotlin Project and put in an empty Kotlin file/class, paste this code into the empty class and
// select Run... from the Run menu and pick this program
// The program should run in a Run window which is a JVM (Java Virtual Machine)
// A person familiar with the IntelliJ IDEA IDE or books on the topic may be helpful in getting this code to run (to check details and nuances).
// In computing it is common to be one or two keystrokes away from having a program run. A person that already knows the computing system
// can usually figure out fast how to get a program running.
// Whether you never programmed or are an expert you learn the most when you teach others

// Array, random numbers, logic, syntax, null values, nullable types, functions, default function values, classes, objects, try and catch,
// screen input and screen output examples are shown in this code.
// Variable names are semi-self descriptive to show their purpose.
// Question marks in the code are the Kotlin way setting null values. For reasons that will be easier to understand by writing some code yourself
// null values are a often good option to use in Kotlin code, and depending on what the code is for, it is sometimes necessary to use null values in Kotlin
// Variable names in Kotlin can (and) usually do have computer address numbers. The address numbers are not usually displayed or known to the programmer
// The address numbers are the computer memory location of where the value (or more broadly object) is that the variable refers to

// For example in the line of code "x = 5"
// x is the variable and x has an address which is a usually an 8 digit hexadecimal number that isn't shown.
// The Kotlin computer language compiler picks an address automatically. The programmer does not decide the what the address is.
// For example it could be 1f8c03ba and 1f8c03ba is a location or address in the computer memory where the number value '5' is stored
// The value '5' can be changed to another number value
// For example 'x = 6' or 'x = 100' but that number value (6 or 100 in these examples)
// will now be stored at address (or location) 1f8c03ba instead of the number 5
// If a place is in memory is needed for a number value but it isn't yet known what the number will be, a null value can be used in place of a number
// The next line sets an integer variable call 'x' equal to null

// var x: Int? = null

// This means x is an integer (Int type) and is also a variable (var) which instead of being set to number (for example 6 or 100)
// x is set to null. The address for x is an 8 digit hexadecimal number (for example 1f8c03ba) but there is nothing at this address, it is null.
// The code line
// var x: Int? = null
// reserves an address (or location, for example 1f8c03ba) in the computers memory that does not yet store an integer (for example 5, or 6 or 100)
// but an integer can be stored there by another line of code (for example x = 100)
// x represents a computer address also called a location (for example 1f8c03ba) and if x is null it's address (or location) is empty
// but ready to store an integer.


// This program tabulates bowling scores by representing each bowling frame as a collection of objects which are stored in an array.
// The name of the array is 'game'. The type of object is named 'BowlingFrame'

// This code can best be understood by making advance visits to a bowling alley and observing or participating in the sport.
// Bowling alleys with an option for bowlers to keep score with a pencil and paper are most suitable for understanding the bowling score system.
// The author has found other bowlers are prompt to check the score keepers accuracy and provide council.


// This imported class "NumberFormatException" is used in a "try catch" code block in case incompatible characters or numbers are input.
import java.lang.NumberFormatException

fun main (args: Array<String>) {
    println("Welcome to Bruce and Bob and Bill and Bruce and Doug and Dara and Jim and Ethyl's Outdoor on the Beach Bowling Lane and Alleys")
    println("Somewhere in the South Pacific\n")
    println()

    //Using proper grammar in these comments would not be a good way to explain this code
    //The unusual grammar in theses comments is partly due to that some readers are not used to reading code and
    //the nature of putting comments in code, is different than other kinds of writing
    //but if it is re-read slowly most of it becomes easier to understand. Some parts are not explained well because it would take and a great
    //amount writing and sketch drawings

    //When prompted enter the pin score for the ball rolled - if the pin score is not in range the program will automatically bowl a pin score

    //These two values adjust the number of pins per frame and the number of frames.
    //For example a game could be set to have 5000 pins and 3 frames.
    //It is recommended that the potentialNumberOfFrames be set to 3 or more and the number of pinAmountPerFrame to be 1 or greater
    //for the score keeping algorithm to tabulate normally.
    //For a standard 10 Frame game set the potentialNumberOfFrames to 12. This will create an Array of 12 Frames that being 10 Frames
    //along with an extra 2 frames in case a strike or spare is bowled on the (potentialNumberOfFrames - 2) frame
    //Array elements are numbered beginning with the number zero (not one)
    //game[0] is the first element of the array of "BowlingFrame"s. game[1] is the second element of the array of "BowlingFrame"s
    //This offset of "1" should be kept in mind when writing code but all code writing encounters some errors due to this offset

    //val pinAmountPerFrame = 5000
    //val potentialNumberOfFrames = 4

    //For improved scores substitute the code below with the code above.
    //It sets the number of frames to 4 frame game and the number of pins to 5000 for a possible 20,000 point scoring game.

    val pinAmountPerFrame = 10
    val potentialNumberOfFrames = 12

    //This is an example of how to set up an empty (or null) array in Kotlin which can be filled in with values as the program runs
    //It is also the basis of this example program
    val game = arrayOfNulls<BowlingFrame?>(potentialNumberOfFrames)

    //The game score
    var score = 0

    //A function that is called and run for each BowlingFrame of the game.
    fun letsBowl(ball:Int){

        var numberOfSetPins = pinAmountPerFrame
        val ballOne:Int?
        val ballTwo:Int?
        println("Bowling Frame Number ${ball + 1}")

        print("Ball One ")
        //check the first ball roll and set the pin number for the second ball roll.
        ballOne = rollBall(numberOfSetPins)
        numberOfSetPins = numberOfSetPins - ballOne!!

        //Calculate score if 2 strikes in a row have proceeded the first ball of the current frame.
        if (ball>1&&game[ball-1]?.strike!!&&game[ball-2]?.strike!!){
            game[ball-2]?.adjustedFrameScore = game[ball-2]?.frameScore!! + game[ball-1]?.ballNumberOne!! + ballOne
            score = score + game[ball-2]?.adjustedFrameScore!!
        }

        //Calculate Score if a spare has proceeded the first ball of the current frame.
        if (ball>0&&game[ball-1]?.spare!!&&ball - 1 < potentialNumberOfFrames - 2){
            game[ball-1]?.adjustedFrameScore = game[ball-1]?.frameScore!!  + ballOne
            score = score + game[ball-1]?.adjustedFrameScore!!
        }

        //Calculate score for a strike (or if the first extra frame is proceeded by a spare - the spare and strike conditions are set by an if statement elsewhere in the code) and set frameStatue variable properties of the frame.
        if (ballOne == pinAmountPerFrame||(game[potentialNumberOfFrames-3] != null && game[potentialNumberOfFrames-3]?.spare!!)||ball == potentialNumberOfFrames - 1){
            //Only 2 of 3 arguments (in this case pinAmountPerFrame and ballOne) (also knows as parameters) are
            //passed to the BowlingFrame() constructor. As this example shows ,in Kotlin it is not necessary to pass all arguments to a function.
            //Unintended arguments can be passed to function variables therefore it is recommended when writing Kotlin programs to
            //to check for this possibility in order to avoid unexpected output.
            game[ball] = BowlingFrame(pinAmountPerFrame, ballOne)
            game[ball]?.setDefaultAndAdjustedFrameScore()
            if (ballOne == pinAmountPerFrame) game[ball]?.strike = true
        }else{
            //If the BallOne is not a strike, roll a second ball and update the ballNumberOne, ballNumberTwo and frameStatus variable properties of the BowlingFrame object.
            print("Ball Two ")
            ballTwo = rollBall(numberOfSetPins)
            game[ball] = BowlingFrame(pinAmountPerFrame,ballOne, ballTwo)
            game[ball]?.setDefaultAndAdjustedFrameScore()
            if (ballTwo!! == numberOfSetPins){
                game[ball]?.spare = true
            }
            else{
                game[ball]?.open = true
            }

            if (ball>0&&game[ball-1]?.strike!!){
                game[ball-1]?.adjustedFrameScore = game[ball-1]?.adjustedFrameScore!! + game[ball]?.frameScore!!
                score = score + game[ball-1]?.adjustedFrameScore!!
            }
        }
        //This line calculates the score on the conditions of both the BowlingFrame property open being true and the ball number being
        //less than potentialNumberOfFrames - 1. For example if a strike is bowled in the 10th frame of a 10 frame game of bowling
        //2 more balls will be rolled. If both of those balls are strikes then the game is complete, the 10, 11th, and 12th frames being strikes
        //In no possible circumstance of a 10 frame game is a second ball of the 12th frame to be rolled for scoring purposes.
        //In non-virtual Bowling Alleys this ball is sometimes rolled for practice or amusement but it is not an official part of the game.
        //The points from a second ball of the 12th frame are not applied to the score.
        //In other words in all cases of score keeping in the game of bowling
        //The score of the second ball of the highest potential frame is not applied to the score.
        //The condition 'ball<potentialNumberOfFrames -1' ensures this.
        if (game[ball]?.open!!&&ball<potentialNumberOfFrames - 2) score = score + game[ball]?.adjustedFrameScore!!

        //Display Frame status, and if the frame is open display the score.
        if (game[ball]?.ballNumberTwo == null){
            println("${game[ball]?.frameStatus}")
        }else{
            println(if (game[ball]?.open!!) "${game[ball]?.frameStatus} ${score}" else "${game[ball]?.frameStatus}")
        }
    }

    println("Please visit www.cometfractals.com for paid apps, free apps and more\n")
    println("This is a ${potentialNumberOfFrames - 2} Frame Game with ${pinAmountPerFrame} pins per frame")

    //This is actually the main loop of the program
    for (bowlBall in 0..potentialNumberOfFrames - 1)
    {
        if (bowlBall<potentialNumberOfFrames-2||(bowlBall == potentialNumberOfFrames - 2&&!game[potentialNumberOfFrames-3]?.open!!)||((bowlBall == potentialNumberOfFrames - 1)&&(game[potentialNumberOfFrames-3]?.strike!!&&game[potentialNumberOfFrames-2]?.strike!!))) letsBowl(bowlBall)
    }
    //Print the game score
    println("The Score is ${score}\n")
    println("Please visit www.cometfractals.com for paid apps, free apps, commentary, mathematics and more")
}

//This is an example of both a "try and catch" code block and readln() to get user input from a keyboard.
fun rollBall(pins: Int): Int? {

    val pinNumber = pins
    var ball: Int? = null

    try {
        ball = readLine()?.toInt()!!
        if (ball<0||ball>pinNumber) ball = autoBowl(pinNumber)
    } catch (e: NumberFormatException) {
        ball = autoBowl(pinNumber)
    }
    return ball
}

//This is an example of generating a random number in Kotlin.
fun autoBowl(numberOfPins:Int) :Int{
    var pinAmount:Int? = null
    if (numberOfPins>0) pinAmount = kotlin.random.Random.nextInt(numberOfPins + 1)
    println("AutoBowl ${pinAmount}")
    return pinAmount!!
}

//This code block is an example of making a data type object in Kotlin
//The object here is made from the class BowlingFrame and therefore is of a type called BowlingFrame.
//A BowlingFrame object represents a frame in a game of bowling
//This program uses an Array of BowlingFrame objects to represent the frames of a Bowling game.
//The question marks mean that values can be null.
class BowlingFrame(var fullPinNumber:Int, var ballOne: Int? = null, var ballTwo: Int? = null) {
    var ballNumberOne: Int? = ballOne
    var ballNumberTwo: Int? = ballTwo
    var frameScore: Int? = null

    var adjustedFrameScore: Int? = null
    var frameStatus = "open   -"

    var spare = false
    var strike = false
    var open = false

    fun setDefaultAndAdjustedFrameScore() {
        if (ballNumberTwo == null) {
            frameScore = ballNumberOne!!
            // If a second ball is not rolled on any frame that is less than the (fullPinNumber minus 2) then the FrameStatus is a strike
            // however it is not necessarily a strike if a second ball is not rolled on frames subsequent to (fullPinNumber minus 2)
            if (ballNumberOne == fullPinNumber) frameStatus = "strike    X"
        } else {
            frameScore = ballNumberOne!! + ballNumberTwo!!
            if (frameScore == fullPinNumber) frameStatus = "spare    /"
        }
        //This assures that AdjustedFrameScore is not null
        adjustedFrameScore = frameScore
    }
}
//This code is for teaching KOtlin arrays and is not set up for structure or efficiency. This is partly because if it
//were set up to for structure and speed it might not be as informative on the matter of showing how to set up a Kotlin array.

//A distinctive characteristic of score keeping in bowling is that in the frames allotted by default
//(for example that is the 10 frames in a standard 10 frame game) a single pin can give 1, 2 or 3 points
//depending on whether the frames are open, spares or strikes. However in the extra frames
//(for example the possible 11th and 12th frames of a 10 frame game) a pin only gives 1 point
//This is one of the reasons that writing a program to keep track of bowling scores is not
//as straightforward as might be expected and is also a good way to understand computer programming.
