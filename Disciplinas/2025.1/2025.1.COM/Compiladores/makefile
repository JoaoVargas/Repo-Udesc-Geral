all:
	alex Lex.x -o Lex.hs
	happy Parser.y -o Parser.hs
	ghc -package array -o compilador Main.hs Token.hs Lex.hs Parser.hs

clean:
	rm -f *.hi *.o  Lex.hs Parser.hs compilador

run:
	./compilador teste.txt