package paveena.mrr.ac.th.porreadcode.fragment;

import android.icu.text.Replaceable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import paveena.mrr.ac.th.porreadcode.R;
import paveena.mrr.ac.th.porreadcode.utility.GetAllUser;
import paveena.mrr.ac.th.porreadcode.utility.MyAlert;
import paveena.mrr.ac.th.porreadcode.utility.MyConstant;

/**
 * Created by Teacher on 20/3/2561.
 */

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Register Conyroller
        registerConyroller();

//        Login Controller
//        loginController();

        test();


    }   // Main  Method

    private void test() {

        String[] logingStrings = new String[]{"20", "por", "porpor", "1234"};

        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "");


            }
        });

    }

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passEditText = getView().findViewById(R.id.edtPasswird);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passEditText.getText().toString().trim();

                if (userString.isEmpty()|| passwordString.isEmpty())  {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.have_space),
                            getString(R.string.message_have_space));


                } else {
//                    No Space

                    try {

                        MyConstant myConstant = new MyConstant();
                        GetAllUser getAllUser = new GetAllUser(getActivity());
                        getAllUser.execute(myConstant.getUrlGetAllUserString());

                        String jsonString = getAllUser.get();
                        Log.d("22MarchV1", "JSON ==> " + jsonString);

                        String[] columnUserStrings = myConstant.getLoginStrings();
                        String[] loginStrings = new String[columnUserStrings.length];
                        boolean statusBool = true;

                        JSONArray jsonArray = new JSONArray(jsonString);

                        for (int i=0; i<jsonArray.length();i+=1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (userString.equals(jsonObject.getString(columnUserStrings[2]))) {

                                statusBool = false;
                                for (int i1=0; i1<columnUserStrings.length; i1+=1) {
                                    loginStrings[i1] = jsonObject.getString(columnUserStrings[i1]);
                                    Log.d("22MarchV1", "logingString[" + i1 + "] ==> " + loginStrings[i1]);
                                }

                            }   // if


                        }   // for

                        if (statusBool) {
//                            User False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog( "User False",
                                    "No This User in mySQL");

                        } else if (passwordString.equals(loginStrings[3])) {
//                            Password True
                            Toast.makeText(getActivity(),"Welcome" + loginStrings[1],
                                    Toast.LENGTH_SHORT).show();


                        } else {
//                            Password False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("Password False",
                                    "Please Try Again Password False");


                        }





                    } catch (Exception e){
                        e.printStackTrace();
                    }





                }   // if



            }   // onClik
        });
    }

    private void registerConyroller() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();


            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R. layout.fragment_maim, container, false);

        return view;
    }
}  // Main Class
