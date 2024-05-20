	.text
	.globl main
main:
	li $v0, 5 # le imput do usuario
    	syscall
	move $s1, $v0 # move o input pro $s1
	li $s0, 1 # $s0 = 1
while:
	mul $s0, $s0, $s1 # $s0 = $s0 * $s1 (32 bits baixos)
	subi $s1, $s1, 1 # $s1 = $s1 - 1
	bnez $s1, while # se $s1 != 0 ent˜ao v´a p/ while
print:
	li $v0, 1
	move $a0, $s0
	syscall # encerra o programa
end:
	li $v0, 10 # C´odigo para encerrar o programa
	syscall # encerra o programa