PGDMP      5                |            ban2Ex2b    16.2    16.1                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16458    ban2Ex2b    DATABASE     v   CREATE DATABASE "ban2Ex2b" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE "ban2Ex2b";
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    16459    Curral    TABLE     n   CREATE TABLE public."Curral" (
    numero bigint NOT NULL,
    localizacao character varying(100) NOT NULL
);
    DROP TABLE public."Curral";
       public         heap    postgres    false    4            �            1259    16464 
   Tratamento    TABLE     l   CREATE TABLE public."Tratamento" (
    data date NOT NULL,
    descricao character varying(100) NOT NULL
);
     DROP TABLE public."Tratamento";
       public         heap    postgres    false    4            �            1259    16469    Veterinario    TABLE     �   CREATE TABLE public."Veterinario" (
    crm bigint NOT NULL,
    nome character varying(100) NOT NULL,
    convenio bigint NOT NULL
);
 !   DROP TABLE public."Veterinario";
       public         heap    postgres    false    4                      0    16459    Curral 
   TABLE DATA                 public          postgres    false    215   v                 0    16464 
   Tratamento 
   TABLE DATA                 public          postgres    false    216   �                 0    16469    Veterinario 
   TABLE DATA                 public          postgres    false    217   �       z           2606    16463    Curral Curral_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Curral"
    ADD CONSTRAINT "Curral_pkey" PRIMARY KEY (numero);
 @   ALTER TABLE ONLY public."Curral" DROP CONSTRAINT "Curral_pkey";
       public            postgres    false    215            |           2606    16468    Tratamento Tratamento_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public."Tratamento"
    ADD CONSTRAINT "Tratamento_pkey" PRIMARY KEY (data);
 H   ALTER TABLE ONLY public."Tratamento" DROP CONSTRAINT "Tratamento_pkey";
       public            postgres    false    216            ~           2606    16473    Veterinario Veterinario_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public."Veterinario"
    ADD CONSTRAINT "Veterinario_pkey" PRIMARY KEY (crm);
 J   ALTER TABLE ONLY public."Veterinario" DROP CONSTRAINT "Veterinario_pkey";
       public            postgres    false    217               
   x���             
   x���             
   x���         