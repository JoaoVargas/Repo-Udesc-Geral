.text
.globl main

main:
	jal get_int # Le um input	
	move $a0, $v0 # Move o input pro $a0
	jal sum # Chama a função fatorial
	move $a0, $v0 # Move o resultado pro $a0
	jal print_int # Printa o resultado no $a0
end:
	li $v0, 10
	syscall
sum:
	bltz $a0, sum_zero	
	
	addi $sp, $sp, -8 # Aloca espaço na pilha para salvar $ra e $s0
	sw $ra, 0($sp)    # Salva $ra na pilha
	sw $a0, 4($sp)    # Salva $s0 na pilha
	
	addi $a0, $a0, -1

	jal sum
	
	# Retorna o resultado
	lw $ra, 0($sp) # Recupera $ra da pilha
	lw $a0, 4($sp) # Recupera $s0 da pilha
	addi $sp, $sp, 8 # Desaloca espaço na pilha
	add $t0, $t0, $a0
	move $v0, $t0

	jr $ra	
sum_zero:
	jr $ra
print_int:
	li	$v0, 1
	syscall
	jr	$ra
get_int:
	li $v0, 5
	syscall
	jr	$ra