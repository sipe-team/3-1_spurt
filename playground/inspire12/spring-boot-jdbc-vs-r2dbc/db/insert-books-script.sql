do $$
begin
   for counter in 1..3000 loop
	raise notice 'counter: %', counter;
	insert into "library".books (title,author,isbn,"year") values('MediumPubblication','Me',11111111,counter);
   end loop;
end; $$