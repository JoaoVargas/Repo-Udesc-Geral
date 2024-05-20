	.data
nl: 	.asciiz 	"\n"
err: 	.asciiz		"ERRO"
	.text
	.globl main
main:
	li $s1, 0
	li $s2, 0
	
loop:
	li $v0, 5 # ativa ler input do usuario
    	syscall
	move $s0, $v0 # move o input pro $s0
	
	beq $s0, -1, checkending # se $0 = -1 vai para checar
	
	add $s1, $s1, $s0 # $s1 = $s1 + $s0
	add $s2, $s2, 1 # $s2 = $s2 + 1
	
	b loop # volta loop
checkending:
	bgtz $s1, goodending
	
badending:
	li $v0, 1 # ativa printar inteiro
	move $a0, $s1
	syscall # printa
	
	li $v0, 4 # ativa printar string
	la $a0, nl
	syscall # printa
	
	li $v0, 4 # ativa printar string
	la $a0, err
	syscall # printa

	li $v0, 10 # Código para encerrar o programa
	syscall # encerra o programa
goodending:
	div $s0, $s1, $s2 # $s0 = $s1 / $s2
	
	li $v0, 1 # ativa printar inteiro
	move $a0, $s1
	syscall # printa
	
	li $v0, 4 # ativa printar string
	la $a0, nl
	syscall # printa
	
	li $v0, 1 # ativa printar inteiro
	move $a0, $s0
	syscall # printa

	li $v0, 10 # Código para encerrar o programa
	syscall # encerra o programa
	

