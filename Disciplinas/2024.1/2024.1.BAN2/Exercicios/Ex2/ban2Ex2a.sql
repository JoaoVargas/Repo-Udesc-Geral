PGDMP       4                |            ban2Ex2    16.2    16.1     -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            0           1262    16388    ban2Ex2    DATABASE     u   CREATE DATABASE "ban2Ex2" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE "ban2Ex2";
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            1           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    16413    Aluno    TABLE     L   CREATE TABLE public."Aluno" (
    ssn bigint NOT NULL,
    turma integer
);
    DROP TABLE public."Aluno";
       public         heap    postgres    false    4            �            1259    16428 	   AlunoGrad    TABLE     �   CREATE TABLE public."AlunoGrad" (
    ssn bigint NOT NULL,
    faculdade character varying(100) NOT NULL,
    grau character varying(100) NOT NULL,
    ano character varying(100) NOT NULL
);
    DROP TABLE public."AlunoGrad";
       public         heap    postgres    false    4            �            1259    16448    Banca    TABLE     ^   CREATE TABLE public."Banca" (
    docente bigint NOT NULL,
    "alunoGrad" bigint NOT NULL
);
    DROP TABLE public."Banca";
       public         heap    postgres    false    4            �            1259    16403    Docente    TABLE     �   CREATE TABLE public."Docente" (
    ssn bigint NOT NULL,
    "fFone" character varying(13) NOT NULL,
    escritorio character varying(100) NOT NULL,
    categoria character varying(100) NOT NULL,
    salario integer NOT NULL
);
    DROP TABLE public."Docente";
       public         heap    postgres    false    4            �            1259    16438 
   Orientador    TABLE     c   CREATE TABLE public."Orientador" (
    docente bigint NOT NULL,
    "alunoGrad" bigint NOT NULL
);
     DROP TABLE public."Orientador";
       public         heap    postgres    false    4            �            1259    16396    Pessoa    TABLE       CREATE TABLE public."Pessoa" (
    ssn integer NOT NULL,
    "pNome" character varying(100) NOT NULL,
    "mInicial" character varying(100) NOT NULL,
    "uNome" character varying(100) NOT NULL,
    "dNasc" date NOT NULL,
    sexo character varying(100) NOT NULL,
    num character varying(10) NOT NULL,
    rua character varying(100) NOT NULL,
    "numApto" character varying(100) NOT NULL,
    cidade character varying(100) NOT NULL,
    estado character varying(100) NOT NULL,
    cep character varying(100) NOT NULL
);
    DROP TABLE public."Pessoa";
       public         heap    postgres    false    4            '          0    16413    Aluno 
   TABLE DATA                 public          postgres    false    217   �       (          0    16428 	   AlunoGrad 
   TABLE DATA                 public          postgres    false    218          *          0    16448    Banca 
   TABLE DATA                 public          postgres    false    220   /       &          0    16403    Docente 
   TABLE DATA                 public          postgres    false    216   I       )          0    16438 
   Orientador 
   TABLE DATA                 public          postgres    false    219   c       %          0    16396    Pessoa 
   TABLE DATA                 public          postgres    false    215   }       �           2606    16432    AlunoGrad AlunoGrad_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public."AlunoGrad"
    ADD CONSTRAINT "AlunoGrad_pkey" PRIMARY KEY (ssn);
 F   ALTER TABLE ONLY public."AlunoGrad" DROP CONSTRAINT "AlunoGrad_pkey";
       public            postgres    false    218            �           2606    16417    Aluno Aluno_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."Aluno"
    ADD CONSTRAINT "Aluno_pkey" PRIMARY KEY (ssn);
 >   ALTER TABLE ONLY public."Aluno" DROP CONSTRAINT "Aluno_pkey";
       public            postgres    false    217            �           2606    16452    Banca Banca_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public."Banca"
    ADD CONSTRAINT "Banca_pkey" PRIMARY KEY (docente, "alunoGrad");
 >   ALTER TABLE ONLY public."Banca" DROP CONSTRAINT "Banca_pkey";
       public            postgres    false    220    220            �           2606    16407    Docente Docente_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public."Docente"
    ADD CONSTRAINT "Docente_pkey" PRIMARY KEY (ssn);
 B   ALTER TABLE ONLY public."Docente" DROP CONSTRAINT "Docente_pkey";
       public            postgres    false    216            �           2606    16442    Orientador Orientador_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public."Orientador"
    ADD CONSTRAINT "Orientador_pkey" PRIMARY KEY (docente);
 H   ALTER TABLE ONLY public."Orientador" DROP CONSTRAINT "Orientador_pkey";
       public            postgres    false    219            �           2606    16402    Pessoa Pessoa_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public."Pessoa"
    ADD CONSTRAINT "Pessoa_pkey" PRIMARY KEY (ssn);
 @   ALTER TABLE ONLY public."Pessoa" DROP CONSTRAINT "Pessoa_pkey";
       public            postgres    false    215            �           2606    16453    Banca Banca_docente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."Banca"
    ADD CONSTRAINT "Banca_docente_fkey" FOREIGN KEY (docente) REFERENCES public."Docente"(ssn);
 F   ALTER TABLE ONLY public."Banca" DROP CONSTRAINT "Banca_docente_fkey";
       public          postgres    false    216    4232    220            �           2606    16443 "   Orientador Orientador_docente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."Orientador"
    ADD CONSTRAINT "Orientador_docente_fkey" FOREIGN KEY (docente) REFERENCES public."Docente"(ssn);
 P   ALTER TABLE ONLY public."Orientador" DROP CONSTRAINT "Orientador_docente_fkey";
       public          postgres    false    219    216    4232            �           2606    16408    Docente ssn_fkey    FK CONSTRAINT     q   ALTER TABLE ONLY public."Docente"
    ADD CONSTRAINT ssn_fkey FOREIGN KEY (ssn) REFERENCES public."Pessoa"(ssn);
 <   ALTER TABLE ONLY public."Docente" DROP CONSTRAINT ssn_fkey;
       public          postgres    false    215    216    4230            �           2606    16418    Aluno ssn_fkey    FK CONSTRAINT     o   ALTER TABLE ONLY public."Aluno"
    ADD CONSTRAINT ssn_fkey FOREIGN KEY (ssn) REFERENCES public."Pessoa"(ssn);
 :   ALTER TABLE ONLY public."Aluno" DROP CONSTRAINT ssn_fkey;
       public          postgres    false    217    4230    215            �           2606    16433    AlunoGrad ssn_fkey    FK CONSTRAINT     r   ALTER TABLE ONLY public."AlunoGrad"
    ADD CONSTRAINT ssn_fkey FOREIGN KEY (ssn) REFERENCES public."Aluno"(ssn);
 >   ALTER TABLE ONLY public."AlunoGrad" DROP CONSTRAINT ssn_fkey;
       public          postgres    false    217    218    4234            '   
   x���          (   
   x���          *   
   x���          &   
   x���          )   
   x���          %   
   x���         