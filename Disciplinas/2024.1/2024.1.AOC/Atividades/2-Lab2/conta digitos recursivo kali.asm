.data
	quebra: .asciiz "\n"
.text
.globl main

main:
	# numero 
	li $v0, 5
	syscall
	move $a0, $v0
	
	# se a0 == 0, vai para o final
	beqz $a0, end
	
	# digito a ser contado
	li $v0, 5
	syscall
	move $a1, $v0
	
	# chama funcao de contar digito solicitado no numero
	jal conta_digitos
	
	move $a0, $v0
	li $v0, 1
	syscall
	
	la $a0, quebra
	li $v0, 4
	syscall
	
	j main

conta_digitos:
	# se a0 == 0, para recursao
	beqz $a0, parada

	# salva contexto na pilha
	addi $sp, $sp, -8
	sw $ra, 0($sp)

	# soma os digitos	
	div $a0 ,$a0, 10 # divide numero por 10 para separar o ultimo digito
	mfhi $t0 #pega o ultimo digito da divisão 
	seq $t1, $a1, $t0 # se o ultimo digito for igual ao digito a ser contado, 1, senao 0
	sw $t1, 4($sp) #salva o 0 ou 1 da operaçao de comparacao

	# chamada recursiva
	jal conta_digitos
	
	#soma as contagens em v0 para retornar
	lw $t0, 4($sp)
	add $v0, $v0, $t0
	lw $ra, 0($sp)
	addi $sp, $sp, 8
	# retorna
	jr $ra
	
parada:
	li $v0, 0
	jr $ra

end:
	li $v0, 10
	syscall