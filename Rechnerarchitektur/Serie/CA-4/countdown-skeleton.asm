# SOLUTIONS
#
# -------------------------------------------------------------------------------------
# Series 4 - MIPS Programming Part 1 - Countdown Clock
# 
# Group members:
# Lukas Ingold
# 
# Individualised code by:
# Lukas Ingold 20-123-998
# 
# Exercise Version:
# ** denote the exercise version here (1, 2) **
#
# Notes:
# We provide hints and guidance in the comments below and
# strongly encourage you to follow the skeleton.
# However, you are free to change the code as you like.
# 
# -------------------------------------------------------------------------------------

# Declare main as a global function
.globl main 
	
# All memory structures are placed after the .data assembler directive
.data

value: .word 10  # this is the initial value of the countdown (seconds)

start: .asciiz "COUNTDOWN STARTED\n"

# *************************************************************************************
# TASK (c): Add the correct hex code for each digit. One byte (8 bits) fully determines
# the state of one digit in the display. See the exercise sheet for a legend. 
# *************************************************************************************
#
# The .byte assembler directive reserves space
# in memory for a single byte (or multiple bytes when separated by comma)
# and assigns that memory location an initial value
# (or a comma separated list of initial values)
# 
# 	      0     1     2     3     4     5     6     7     8     9	.
digits: .byte 0x3F, 0x6, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x7, 0x7F, 0x6F, 0x80

# All program code is placed after the .text assembler directive
.text 		

# The label 'main' represents the starting point
main:
	li      $v0, 4       	# code 4 == print string
	la      $a0, start  	# $a0 == address of the string
	syscall             	# Ask the operating system to print to stdout.
	
	
# *************************************************************************************
# TASK (e): Implement the main countdown loop. It should stop when the countdown 
# reaches zero. From here you should call the subroutines defined below, some of which
# are already complete and some you have to implement in other tasks.
# *************************************************************************************
	
	lw $t0, value  		# register t0 holds the countdown value
	li $s5, 5 		#5 is the amount of blinks
	li $s7, 5		#5 is the amount of blinks for the decimal points
	
	jal loop
	
	
	loop:	
		add $a0, $zero, $t0	#loads the count in to a0 to split
		jal get_digits		#splits up the digit in to tenners and onners 
		li $a1, 0xFFFF0011	#sets the adress of the left display to $a1
		addi $a0, $v1, 0	#sets number the left numbber to a0 register
		jal write_digit		#writes digit in $a0 to display in $a1
		li $a1, 0xFFFF0010	#sets the adress of right display to $a1
		addi $a0, $v0, 0	#sets number the right numbber to a0 register
		jal write_digit		#writes digit in $a0 to display in $a1
		jal wait1s		#waits 1s
		subu $t0, $t0, 1	#subtracts one from the counter
		bgez $t0, loop		#checks loopcount if its bigger or equal to zero it goes back to top of loop
		jal blink		#executes blink
		jal end			#jumps to end if loop count is zero
		
		
		
	wait1s: 
		li $v0, 32       	# code 32 is the code for a wait
		la $a0, 1000  		# $a0 contains the amount of miliseconds
		syscall             	# tells the system to wait 
		jr $ra			# returns to loop
	
	# 1. Split the countdown value into its two digits (see get_digits subroutine)
	# 2. Write the digits to the display (see write_digit subroutine)
	# 3. Stall for 1 second
	# 4. Check if countdown runs out and continue looping
	
	
# *************************************************************************************
# TASK (f): Alarm signal - create a blinking animation. 
# *************************************************************************************

	blink:
		sb $zero, 4294901776 	#Right LED turn off
		sb $zero, 4294901777 	#Left LED turn off
		jal delay		#wait amount of time
		li $a0, 0		#Student 2 should change the value 0 here to 10
		li $a1, 4294901776	#load adress of display in a1
		jal write_digit		#writes digit right
		li $a1, 4294901777	#sets adress to write left digit
		jal write_digit		#write digit left
		jal delay		#wait some time
		sb $zero, 4294901776 	#Right LED turn off
		sb $zero, 4294901777 	#Left LED turn off
		subu $s5 ,$s5, 1	#subtracts one from the loop count
		bgtz $s5, blink		#checks if loopcount is bigger than zero
		jal blink2		#optional programm could end here but for demo purpose jumps to decimal blink
		
	
	blink2:
		sb $zero, 4294901776 	#Right LED turn off
		sb $zero, 4294901777 	#Left LED turn off
		jal delay		#wait amount of time
		li $a0, 10		#Student 2 should change the value 0 here to 10
		li $a1, 4294901776	#load adress of display in a1
		jal write_digit		#writes digit right
		li $a1, 4294901777	#sets adress to write left digit
		jal write_digit		#write digit left
		jal delay		#wait some time
		sb $zero, 4294901776 	#Right LED turn off
		sb $zero, 4294901777 	#Left LED turn off
		subu $s7 ,$s7, 1	#subtracts one from the loop count
		bgtz $s7, blink2	#checks if loopcount is bigger than zero
		jal end			#ends the programm
		
		
		
	delay:
		li $v0, 32       	# code 32 is the code for a wait
		la $a0, 300  		# $a0 contains the amount of miliseconds
		syscall             	# tells the system to wait 
		jr $ra			# returns to loop
		
		
	# 1. Turn display off
	# 2. Wait
	# 3. Turn display on
	# 4. Loop
	
	

	
	
	end:
		li $v0, 10 		# The code for exit is "10". 
		syscall			# Exit the program by means of a syscall.




# This is a helper subroutine. It splits an integer into its two individual digits and
# saves the results in registers v0 and v1.
# Arguments: 
#     a0: an integer value 0 <= x < 100
# Outputs:
#     v0: right digit (10^0)
#     v1: left digit (10^1)
get_digits:
	li $a1, 10		#get 10 into the $a1 register
	divu $a0, $a1 		#divides $a0 by 10 without overflow to get the tens and ones
	mfhi $v0		#loads the upper 32 bits from the product register in to $v0 loads reminder so the right number
	mflo $v1		#loads the lower 32 bits from the product register in to $v1 loads quotient so the left number
	jr $ra			#jumps back to main
	
	
# *************************************************************************************
# TASK (d): Write a digit to the segment display. This subroutine expects two 
# arguments as input, saved in registers a0 and a1. 
# 
# a0 (word): The digit, a number between 0 and 9
#
# a1 (byte): This is the address of the segment status buffer to which we want to 
#	write the segment state. 
# 	This will either be 0xFFFF0010 (right segment) or 0xFFFF0011 (left segment).
#
#
# Hint 1: Make sure to save any temporary registers that you use here to the stack
# and restore them again before returning.
#
# Hint 2: The register "ra" contains the return address
#
# *************************************************************************************
write_digit:
	la $t1, digits($a0)
	lb $t1, ($t1)
	sb $t1, 0($a1)
	jr $ra

	# 1. Fetch the correct byte pattern depending on digit passed in (see the 
	#    array "digits" defined at the top). 
	#    Hint: You can load an address with the "la" instruction.
	# 2. Save the byte to the memory address in a1. 
	#    Hint: You can store a byte with the "sb" instruction. 
	#          Compare it to the "sw" instruction seen in class.
	# 3. Return.
	
