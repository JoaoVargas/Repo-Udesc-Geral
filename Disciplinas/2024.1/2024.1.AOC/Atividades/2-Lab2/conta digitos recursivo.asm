.data
	nl: .asciiz "\n"
.text
.globl main

main:
	jal contador # Chama a função fatorial
end:
	li $v0, 10
	syscall
contador:
    	addi $sp, $sp, -4 # Aloca espaço na pilha para salvar $ra
	sw $ra, 0($sp) # Salva $ra na pilha
	
	j contador_interface
contador_interface:
	jal get_int # Le um input
	move $a0, $v0 # Move o input pro $a0
	
	beqz $a0, contador_fim # se a0 == 0, para recursao	
	
	jal get_int # Le um input
	move $a1, $v0 # Move o input pro $a1
	
	jal contador_recursao
	jal print_int
	jal print_nl

	j contador_interface
contador_recursao:
	# soma os digitos	
	div $a0 ,$a0, 10 # divide numero por 10 para separar o ultimo digito
	mfhi $t0 #pega o ultimo digito da divisão 
	seq $t1, $a1, $t0 # se o ultimo digito for igual ao digito a ser contado, 1, senao 0
	add $t2, $t2, $t1
	
	bnez $a0, contador_recursao
	
	move $a0, $t2 # Move o $t2 pro $v0
	li $t2, 0 # Zera o $t2
	
	jr $ra # retorna
contador_fim:
	lw $ra, 0($sp) # Salva $ra na pilha
    	addi $sp, $sp, 4 # Desaloca espaço na pilha
    	jr $ra
print_int:
	li	$v0, 1
	syscall
	jr	$ra
print_str:
	li $v0, 4
	syscall
	jr	$ra
print_nl:
	la	$a0, nl
	li	$v0, 4
	syscall
	jr	$ra
get_int:
	li $v0, 5
	syscall
	jr	$ra

	
