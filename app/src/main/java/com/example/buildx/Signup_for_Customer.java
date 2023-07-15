package com.example.buildx;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

import com.example.buildx.Models.User_Model;


public class Signup_for_Customer extends AppCompatActivity {

    private int year,month,day;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_for_customer);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        EditText name = findViewById(R.id.Customername);
        EditText mail = findViewById(R.id.cEmail);
        EditText DOB = findViewById(R.id.cDOB);
        EditText contact = findViewById(R.id.cContact);
        EditText address = findViewById(R.id.cAddress);
        EditText password = findViewById(R.id.cpassword);
        EditText password2 = findViewById(R.id.cpassword2);
        Button signup = findViewById(R.id.csignupbtn);

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Signup_for_Customer.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       DOB.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString().trim();
                String mail1 = mail.getText().toString().trim();
                String DOB1 = DOB.getText().toString();
                String contact1 = contact.getText().toString();
                String address1 = address.getText().toString();
                String password1 = password.getText().toString();
                String role = "customer";

                if (name1.isEmpty()) {
                    name.setError("Please Enter Username");
                    if(password1.isEmpty())
                        password.setError("Please Enter Password");
                    if(mail1.isEmpty())
                        mail.setError("Please Enter Mail");
                    if(contact1.isEmpty())
                        contact.setError("Please Enter Contact");
                    if(address1.isEmpty())
                        address.setError("Please Enter Address");
                    if(DOB1.isEmpty())
                        DOB.setError("Please Enter Date of Birth");
                    Toast.makeText(Signup_for_Customer.this, "These are required", Toast.LENGTH_SHORT).show();

                } else {

                    String pass2 = password2.getText().toString().trim();
                    if(password1.equals(pass2)) {

                        firebaseAuth.createUserWithEmailAndPassword(mail1, password1)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(Signup_for_Customer.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), Login_Activity.class));

                                        firebaseFirestore.collection("Customer")
                                                .document(FirebaseAuth.getInstance().getUid())
                                                .set(new User_Model(name1, mail1, DOB1, contact1, address1, password1,role));

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Signup_for_Customer.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else {
                        Toast.makeText(Signup_for_Customer.this, "Please match both the Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


    @Override
    public void onBackPressed() {

        startActivity(new Intent(this,Signup_as_a.class));
    }
}






























//    private static final String url="http://10.0.2.2/login/signup_cus.php";
////    private static final String url="http://localhost/login/signup_cus.php";
////    private static final String url="http://192.168.1.13/login/signup_cus.php";



//private void insertdata() {
//
//        t1 = findViewById(R.id.Customername);
//        t2 = findViewById(R.id.Email);
//        t3 = findViewById(R.id.DOB);
//        t4 = findViewById(R.id.Contact);
//        t5 = findViewById(R.id.Address);
//        t6 = findViewById(R.id.Password);
//
//final String name=t1.getText().toString().trim();
//final String email=t2.getText().toString().trim();
//final String DOB=t3.getText().toString().trim();
//final String contact=t4.getText().toString().trim();
//final String address=t5.getText().toString().trim();
//final String pwd=t6.getText().toString().trim();
//
//        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
//
//        t1.setText("");
//        t2.setText("");
//        t3.setText("");
//        t4.setText("");
//        t5.setText("");
//        t6.setText("");
//
//        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
//
//        }, new Response.ErrorListener() {
//@Override
//public void onErrorResponse(VolleyError error) {
//
//        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
//
//        }
//        }){
//@Override
//protected Map<String, String> getParams() {
//        Map<String,String> param = new HashMap<String,String>();
//        param.put("t1",name);
//        param.put("t2",email);
//        param.put("t3",DOB);
//        param.put("t4",contact);
//        param.put("t5",address);
//        param.put("t6",pwd);
//        return param;
//        }
//
//        };
//
//        RequestQueue  queue = Volley.newRequestQueue(getApplicationContext());
//        queue.add(request);
//
