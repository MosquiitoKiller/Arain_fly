create user "tour-agency-back" superuser login encrypted password 'SCRAM-SHA-256$4096:cQFWS9iiDHREjd8P5q1ulg==$i0iMLe5QARFH1o+Se8KjeHsuX0jRB3NpUJEp5BVFrmI=:0mOWsi6NMrRkHxQI5oPJRbu6JaeYizP6GG3D/9MA4aE=';
create database tour_agency with owner "tour-agency-back";
grant all privileges on database tour_agency to "tour-agency-back";