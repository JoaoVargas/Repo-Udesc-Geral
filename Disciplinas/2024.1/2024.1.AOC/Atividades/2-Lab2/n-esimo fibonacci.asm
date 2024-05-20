.data
	prompt1: .asciiz "Digite número inteiro:\n"
	nl: .asciiz "\n"
.text
.globl main

main:
	la $a0, prompt1 # Move o prompt1 pro $a0
	jal print_str # Printa o prompt
	jal get_int # Le um input
	move $a0, $v0 # Move o input pro $a0
	jal fibonacci # Chama a função fatorial
	move $a0, $v0 # Move o resultado pro $a0
	jal print_int # Printa o resultado no $ao
end:
	li $v0, 10
	syscall
fibonacci:
	blt $a0, 0, fibonacci_erro # Se < 0, retorna 0
	
    	addi $sp, $sp, -8 # Aloca espaço na pilha para salvar $ra e $s0
	sw $ra, 0($sp)    # Salva $ra na pilha
    	sw $s0, 4($sp)    # Salva $s0 na pilha

	li $s0, 0   # Inicializa o primeiro número da sequência de Fibonacci
    	li $v0, 1   # Inicializa o segundo número da sequência de Fibonacci
fibonacci_recursao:
    	add $t0, $s0, $v0   # Calcula o próximo número da sequência
    	move $s0, $v0        # Move o segundo número para o primeiro
    	move $v0, $t0        # Move o próximo número para o segundo
    	addi $a0, $a0, -1    # Decrementa o contador
    	bgtz $a0, fibonacci_recursao # Repete até que o contador seja 0

    	# Retorna o resultado
    	lw $ra, 0($sp) # Recupera $ra da pilha
	lw $s0, 4($sp) # Recupera $s0 da pilha
    	addi $sp, $sp, 8 # Desaloca espaço na pilha
    	jr $ra
fibonacci_erro:
	li  $v0, 0
	jr $ra	# devolve 0 pro $v0
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

	
