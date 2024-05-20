.globl main
.text

main:
	li $v0, 5
	syscall
	move $a0, $v0
	jal fib
	move $a0, $v0
	li $v0,1
	syscall
	j end
fib:
	# se zero retorna 1
	beqz $a0, zero
	# se maior que 1 vai para caso recursivo
	bgt $a0,1, caso_recursivo
	move $v0, $a0
	jr $ra
	
zero:
	li $v0, 1
	jr $ra
	
caso_recursivo:
	# salva contexto
	addi $sp, $sp, -12
	sw $ra, 0($sp)
	sw $a0, 4($sp)
	
	#fib n-1
	addi $a0, $a0, -1
	jal fib
	sw $v0, 8($sp)
	
	#fib n-2
	lw $a0, 4($sp)
	addi $a0, $a0, -2
	jal fib
	
	# restaura contexto e desempilha chamadas de função
	lw $t0, 8($sp)
	add $v0, $v0, $t0
	lw $ra, 0($sp)
	addi $sp, $sp, 12
	jr $ra

end:
	li $v0,10
	syscall