package com.example.quizapppakrecomputerinstitute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizapppakrecomputerinstitute.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizAppAkreComputerInstitute.db";
    private static final int DATABASE_VERSION = 1;
    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static synchronized QuizDbHelper getInstance(Context context) {

        if(instance == null) {

            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategeriesTable.TABLE_NAME + "( " +
                CategeriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategeriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE  " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, "  +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategeriesTable.TABLE_NAME + "(" + CategeriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategeriesTable.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);

        onCreate(db);


    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    private void fillCategoriesTable() {

        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Web");
        insertCategory(c2);
        Category c3 = new Category("Net");
        insertCategory(c3);
        Category c4 = new Category("MSWord");
        insertCategory(c4);

        Category c5 = new Category("Math");
        insertCategory(c5);


    }

    public void addCategory(Category category) {

        db = getWritableDatabase();
        insertCategory(category);


    }
    public  void addCategories(List<Category> categories) {

        db = getWritableDatabase();
        for(Category category : categories) {


            insertCategory(category);
        }
    }


    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategeriesTable.COLUMN_NAME, category.getName());
        db.insert(CategeriesTable.TABLE_NAME, null, cv);



    }

    private void fillQuestionsTable() {

        // MSword Easy

        Question q1 = new Question("MSWord, Easy: In which view Headers and" +
                " Footers are visible?",
                "Normal View", "Page Layout View", "Print Layout View", 3,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q1);
        Question q2 = new Question("MSWord, Easy:Which items are placed at " +
                "the end of a document?",
                "Footer", "End Note", "Header", 2,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q2);
        Question q3 = new Question("MSWord, Easy:Which one can be used us watermark" +
                "in a word document?",
                "Text", "Image", "Both A and B", 3,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q3);
        Question q4 = new Question("MSWord, Easy: Which feature starts a new line whenever" +
                " a word or sentence reached a borde?r",
                "Text line", "Text wrapping", "Text align", 2,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q4);
        Question q5 = new Question("MSWord, Easy:Which item is printed at the bootom" +
                "of each page?",
                "Footer", "Header", "Title", 1,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q5);
        Question q6 = new Question("MSWord, Easy:Color and pattern used to fill" +
                "a closed shape is called?",
                "Shape", "Word art", "Fill style", 3,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q6);
        Question q7 = new Question("MSWord, Easy:Cut selected text? ",
                "Ctr+C", "Ctr+V", "Ctr+X", 3,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q7);
        Question q8 = new Question("MSWord, Easy:Which shortcut use to open options" +
                "in microsoft word?",
                "Ctr+O", "Ctr+V", "Ctr+A", 1,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q8);
        Question q9 = new Question("MSWord, Easy:Which of these shortcuts" +
                "use to Open the print window?",
                "Ctr+M", "Ctr+F", "Ctr+P", 3,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q9);

        Question q10 = new Question("MSWord, Easy: Ctr+A use to? ",
                "Cuts selected item", "Select all contents of the page", "Open options", 2,
                Question.DIFFICULTY_EASY, Category.MSWord);
        insertQuestion(q10);

        //Msword Medium

        Question q11 = new Question("MSWord, Medium:To apply center alignment to a" +
                "paragraph we can press?",
                "Ctr+S", "Ctr+E", "Ctr+C", 2,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q11);
        Question q12 = new Question("MSWord, Medium:Text-styling feature of MS word is?" ,
                "WordColor", "WordArt", "WordFill", 2,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q12);
        Question q13 = new Question("MSWord, Medium:A number of letter that appears" +
                "little above the normal text is called?",
                "SuperScript", "SuperText", "TopText", 1,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q13);
        Question q14 = new Question("MSWord, Medium:We can change the thickness of " +
                "a line from____?",
                "Line Width", "Line style", "Line height", 2,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q14);
        Question q15 = new Question("MCWord, Medium:Ctr+B use to____?",
                "Bold highlighted selection", "Cut selected text", "Open find box", 1,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q15);
        Question q16 = new Question("MCWord, Medium:What item contains detailed information " +
                "about something in the text?",
                "Footer", "Foot Note", "Head Note", 2,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q16);
        Question q17 = new Question("MSWord, Medium:Open new/blank document___?" ,
                "Ctr+Y", "Ctr+U", "Ctr+N", 3,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q17);
        Question q18 = new Question("MSWord, Medium:Redo the last action performed?",
                "Ctr+Z", "Ctr+Y", "Ctr+O", 2,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q18);
        Question q19 = new Question("MSWord, Medium:Ctr+G use to______?",
                "Find and replace option", "Undo last action", "Redo last option", 1,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q19);
        Question q20 = new Question("MSWord, Medium:Bold highlighted selection?",
                "Ctr+F", "Ctr+B", "Ctr+V", 2,
                Question.DIFFICULTY_MEDIUM, Category.MSWord);
        insertQuestion(q20);


        // MsWord Hard
        Question q21 = new Question("MSWord, Hard:The process of removing unwanted part" +
                "of an image is called?",
                "Hiding", "Cropping", "Cutting", 2,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q21);
        Question q22 = new Question("MSWord, Hard:To apply center alignment to a" +
                "paragraph we can press?",
                "Ctr+S", "Ctr+C+A", "Ctr+E", 3,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q22);
        Question q23 = new Question("MSWord, Hard:The space left between the margin " +
                "and the start of a paragraph is called?",
                "Indentation", "Gutter", "Spacing", 1,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q23);
        Question q24 = new Question("MSWord, Hard:To change line to 1.5 we " +
                "use shortcut key:?",
                "Ctr+1", "Ctr+5", "Ctr+3", 2,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q24);
        Question q25 = new Question("MSWord, Hard:Which one can be used as watermark " +
                "in a word document?",
                "Text", "Image", "Both A and B", 3,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q25);
        Question q26 = new Question("MSWord, Hard:Which item appears dimly behid the " +
                "main body text?",
                "Water Color", "WaterMark", "Back Color", 2,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q26);
        Question q27 = new Question("MSWord, Hard:The direction of a rectangular page " +
                "for viewing and printing is called?",
                "Orientation", "Preview", "Direction", 1,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q27);
        Question q28 = new Question("MSWord, Hard:We can remove/hide border of a shape " +
                "by selecting...?",
                "No Outline", "No Border", "No Line", 1,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q28);
        Question q29 = new Question("MSWord, Hard:Where footnotes appear in a document? ",
                "Bottom of document", "Bottom of a page", "None", 2,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q29);
        Question q30 = new Question("MSWord, Hard:Single space line?",
                "Ctr+1", "Ctr+2", "Ctr+1", 2,
                Question.DIFFICULTY_HARD, Category.MSWord);
        insertQuestion(q30);

        //Programming Easy

        Question q31 = new Question("Programming, Easy:Which is a comment?",
                "/ This code accomplishes", "// This code accomplishes", "*/ This code accomplishes", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q31);


        Question q32 = new Question("Programming, Easy:Which is an example of a while statement? " ,
                "A code while { System.out.println(count); count++; }", "for(count<10) { System.out.println(count);count++;}", "while (count<10){System.out.println(count);count++; }", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q32);

        Question q33 = new Question("Programming, Easy:Notes you can write in the code for human readers, but not executed by java ",
                "Bytecode", "Comments", "Syntax", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q33);

        Question q34 = new Question("Programming, Easy:How do you write a single-line comment?",
                "/* followed by the comment", "/ followed by the comment", "// followed by the comment", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q34);

        Question q35 = new Question("Programming, Easy:Given for (int i=0;i<5;i++){ System.out.println(i);} which numbers would be printed to the screen?",
                "0, 1, 2, 3, 4, and 5", "1, 2, 3, and 4", "0, 1, 2, 3,  and 4", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q35);

        Question q36 = new Question("Programming, Easy:This stores multiple items in a single variable?",
                "Operator", "String", "Array", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q36);

        Question q37 = new Question("Programming, Easy:A c++ code line ends with___?",
                "A semicolon(;)", "A fullstop(.)", "A slash(/)", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q37);

        Question q38 = new Question("Programming, Easy:Which is an example of an if-then statement?" ,
                "if (number == 7) then{ system.out.println(you won!);}", "if (number == 7){ system.out.println(you won!);}", "if{ number == 7; system.out.println(you won!);}", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q38);

        Question q39 = new Question("Programming, Easy:A cpp, members of a class ____are " +
                "by default?",
                "Public", "Private", "Static", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q39);

        Question q40 = new Question("Programming, Easy:Which of these have highest precedence?",
                "()", "++", ">>", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q40);


        //Programming medium
        Question q41 = new Question("Programming, Medium:Which of these is returned by operator '&'?",
                "Integer", "Character", "Float", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q41);

        Question q42 = new Question("Programming, Medium:Data type long literals are appended by____?",
                "Uppercase l", "Lowercase l", "Both a and b", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q42);

        Question q43 = new Question("Programming, Medium:Given if(score>= 50) { grade=pass;} else{grade= fail;} what would be the value of grade if score is 49?",
                "fail", "50", "pass", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q43);


        Question q44 = new Question("Programming, Medium:Which one is a template for creating " +
                "different objects?",
                "An array", "A class", "Method", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q44);

        Question q45 = new Question("Programming, Medium:Given int x=5; boolean result =x>4 && x<6; what is the value of result?",
                "true", "false", "5", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q45);
        Question q46 = new Question("Programming, Medium:Which of these is not a bitwise operator?",
                "&' Operator", "<=' Operator>", "|=' Operator", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q46);
        Question q47 = new Question("Programming, Medium:Which operator returns true  ,if either boolean expression true?",
                "!!", "||", "|",2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q47);
        Question q48 = new Question("Programming, Medium:Which statement transfer execution to " +
                "different parts of your code based on the value an expression?",
                "If", "Switch", "Nested-if", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q48);
        Question q49 = new Question("Programming, Medium:Modulus operator(%) can be applied to which of these?",
                "Integers", "Floating-point numbers", "Both a and b", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q49);
        Question q50 = new Question("Programming, Medium:Given int x=5; boolean result=x>6 || x<6; what is the value of result?",
                "false", "true", "6", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q50);

        //Programming Hard

        Question q51 = new Question("Programming, Hard:In java code the line that begins with " +
                "/* and ends with */ is known as?",
                "Multiline comment", "Single line comment", "Both a and b", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q51);

        Question q52 = new Question("Programming, Hard:Given boolean result = 1==10;? what is the value of result?",
                "true", "10", "false", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q52);
        Question q53 = new Question("Programming, Hard:Given boolean myvar = 6>5; boolean result = !myvar; what is the value of result?",
                "1", "false", "true", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q53);
        Question q54 = new Question("Programming, Hard: A component or part of the statement that evaluates to a single value ?",
                "Block", "Statement", "Expression", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q54);
        Question q55 = new Question("Programming, Hard:A special symbol that performs an operation on variables or values, producing a result?",
                "An Method", "Operator", "Symbol", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q55);
        Question q56 = new Question("Programming, Hard:Which data type has a size of 4 bytes, and stores fractional numbers with up to 7 decimal digits?",
                "byte", "double", "float", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q56);
        Question q57 = new Question("Programming, Hard:How do create a variable?",
                "Specify the type, give it a name, identify its parent, and assign it a value", "Specify the type, give it a name, and assign it a value", " give it a name, and assign it a value", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q57);
        Question q58 = new Question("Programming, Hard:What is java? " +
                "java byte code to be executed",
                "A  shortened way to refer to javascript", "A multi-purpose programming language that can be used to build many types of programs", "A query language for accessing and manipulating database", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q58);
        Question q59 = new Question("Programming, Hard:Who is known as father of java " +
                "programming language?",
                "James gosling", "M. p java", "Blais pascal", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q59);
        Question q60 = new Question("Programming, Hard:Given int remainder = 5%2; what is the value of remainder? ",
                "1", "2.5", "2", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q60);


        //net Easy

        Question q61 = new Question("Net, Easy:Php stands for?",
                "Php hypertex processor", "Php hyper markup processor", "Php hypertext preprocessor", 3,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q61);

        Question q62 = new Question("Net, Easy:Php is an example of___ scripting language?",
                "Server-side", "In-side", "Client-side", 1,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q62);
        Question q63 = new Question("Net, Easy:Who is known as the father of php?",
                "Rasmus lerdorf", "list barely", "Derk kolkevi", 1,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q63);
        Question q64 = new Question("Net, Easy:Which of the following is not true?",
                "Php can be used to develop web applications?", "Php applications can not be compile", "Php can not be embedded int html", 3,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q64);
        Question q65 = new Question("Net, Easy:Php scripts are enclosed within___?",
                "<php>...</php>", "<?php...?>", "<p>...</p>", 2,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q65);
        Question q66 = new Question("Net, Easy:Abbrevation AP is__?",
                "Access point", "Authentication header", "Autonomous system", 1,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q66);
        Question q67 = new Question("Net, Easy:Internet protocol is___?",
                "Ip", "Iv", "Is", 1,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q67);
        Question q68 = new Question("Net, Easy:Network interface card is___?",
                "Nic", "Nist", "Nat", 1,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q68);
        Question q69 = new Question("Net, Easy:The abbrevation GSM is___?",
                "General packet radio service", "Global system for mobile", "None of these", 2,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q69);
        Question q70 = new Question("Net, Easy:Transmission control protocol is___?",
                "Tcp", "Tld", "Ttl", 1,
                Question.DIFFICULTY_EASY, Category.Net);
        insertQuestion(q70);

        //Net Medium

        Question q71 = new Question("Net, medium:Authentication Header is___?",
                "AM", "AH", "AS", 2,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q71);

        Question q72 = new Question("Net, medium:Security association is___?",
                "SN", "ST", "SA", 3,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q72);

        Question q73 = new Question("Net, medium:Wide area network is___?",
                "Wan", "lan", "Man", 1,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q73);

        Question q74 = new Question("Net, medium:What is the full form of ddl?",
                "Dynamic data language", "Data derviation language", "Data definition language", 3,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q74);

        Question q75 = new Question("Net, medium:Which statement in sql allows us to change the " +
                "definition of a table?",
                "ALTER", "UPDATE", "SELECT", 1,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q75);

        Question q76 = new Question("Net, medium:What are the different view to present a table?",
                "Datasheet view", "Design view", "All of above", 3,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q76);

        Question q77 = new Question("Net, medium:In one-to-many relationship the table on 'many' " +
                "side is called?",
                "Parent", "Child", "Sister", 2,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q77);

        Question q78 = new Question("Net, medium:Which of the following enables us to view data " +
                "from a table based on a specific criterion?",
                "Form", "Report", "Query", 3,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q78);

        Question q79 = new Question("Net, medium:Which data type allows alphanumeric characters and special " +
                "symbols to be entered?",
                "Text", "Memo", "Auto number", 1,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q79);

        Question q80 = new Question("Net, medium:This key that uniqually identifies each record " +
                "is called?",
                "Unique key", "Primary key", "Field name", 2,
                Question.DIFFICULTY_MEDIUM, Category.Net);
        insertQuestion(q80);

//Net hard
        Question q81 = new Question("Net, Hard:A data dictionary is a repository " +
                "that manages____?",
                "Memory", "Data validator", "Metadata", 3,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q81);

        Question q82 = new Question("Net, Hard:The overall description of a database called___?",
                "Data integrity", "Data manipulation", "Database schema", 3,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q82);
        Question q83 = new Question("Net, Hard:Which of the following is not a database model?",
                "Network database model", "Relational database model", "None", 3,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q83);
        Question q84 = new Question("Net, Hard:Which of the following is not a database object?",
                "Tables", "Reports", "Relationships", 3,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q84);
        Question q85 = new Question("Net, Hard:Which of the following fields has width of 8 bytes:?",
                "Date/time", "Memo", "Number", 1,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q85);
        Question q86 = new Question("Net, Hard:In a database table, the each category og " +
                "information is called____?",
                "Record", "Field", "All of above", 2,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q86);
        Question q87 = new Question("Net, Hard:Code Division Multiple Access is?",
                "CDN", "CDMA", "CDM", 2,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q87);
        Question q88 = new Question("Net, Hard:Asynchronous transfer Mode is?",
                "ATM", "ARQ", "AMT", 1,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q88);
        Question q89 = new Question("Net, Hard:Real Time Streaming Protocol is?",
                "RTM", "RTSP", "RTT", 2,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q89);
        Question q90 = new Question("Net, Hard:The abbrevation SLA is____?",
                "Service level Agreement", "Secure Hash Algorthim", "Service Level", 1,
                Question.DIFFICULTY_HARD, Category.Net);
        insertQuestion(q90);

//Web Easy

        Question q91 = new Question("Web, Easy:Html Stands for?",
                "Hyper text markup language", "High text markup language", "None of these", 1,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q91);

        Question q92 = new Question("Web, Easy:Which of the following tag is used to " +
                "mark a begining of paragraph?",
                "<TD>", "<br>", "<p>", 3,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q92);
        Question q93 = new Question("Web, Easy:From which tag descriptive list starts?",
                "<LL>", "<DD>", "<DL>", 3,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q93);
        Question q94 = new Question("Web, Easy:Correct html tag for the largest" +
                "heading is?",
                "<head>", "<h1>", "<h6>", 2,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q94);
        Question q95 = new Question("Web, Easy:The attribute of <form> tag?",
                "Method", "Action", "Both a and b", 3,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q95);
        Question q96 = new Question("Web, Easy:Web pages starts with which of the" +
                "following tag?",
                "<Body>", "<Title>", "<Html>", 3,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q96);
        Question q97 = new Question("Web, Easy:HTML is a subset of ?",
                "SGMT", "SGML", "SGMD", 1,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q97);
        Question q98 = new Question("Web, Easy:Which of the following is a container?",
                "<SELECT>", "<BODY>", "Both a and b", 3,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q98);
        Question q99 = new Question("Web, Easy:<DT> tag id designed to fit a single line of" +
                "our web page but<DD> tag will accept a?",
                "line of text", "Full paragraph", "Word", 2,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q99);
        Question q100 = new Question("Web, Easy:The tag which allows you to reset other" +
                "html tags within the description is?",
                "<TH>", "<CAPTION>", "<TR>", 2,
                Question.DIFFICULTY_EASY, Category.WEB);
        insertQuestion(q100);

        //Web Medium


        Question q101 = new Question("Web, Medium:Markup tags tell the web browser?",
                "How to organise the page", "How to display the page", "None of these", 2,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q101);


        Question q102 = new Question("Web, Medium:WWW is based on which model?",
                "local-server", "3-tier", "Client-server", 3,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q102);
        Question q103 = new Question("Web, Medium:wWat are empty elements and it is vailed?",
                "No, there is no such terms as empty elements", "Empty elements are element with no data", "None of these", 2,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q103);
        Question q104 = new Question("Web, Medium:Which of the following attributes of text box control" +
                "allow to limit the maximum character?",
                "Size", "len", "Maxlength", 3,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q104);
        Question q105 = new Question("Web, Medium:The attribute, which define the relationship " +
                "between current document and hrffed url is?",
                "REL", "URL", "REV", 1,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q105);
        Question q106 = new Question("Web, Medium:<base>tag is designed to appear only between?",
                "<HEAD>", "<TITLE>", "<BODY>", 1,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q106);
        Question q107 = new Question("Web, Medium:the tag used to create anew list item" +
                "and also include a hyperlink is?",
                "<li>", "<dl>", "<dd>", 1,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q107);
        Question q108 = new Question("Web, Medium:Which of the tag is used to creates a number list?",
                "<li>", "<ol>", "<li>and<ol>", 3,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q108);
        Question q109 = new Question("Web, Medium:<input> is____?",
                "Format tag", "Empty tag", "None of these", 2,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q109);
        Question q110 = new Question("Web, Medium:The latest html standard is?",
                "XML", "SGML", "HTML 5.0", 3,
                Question.DIFFICULTY_MEDIUM, Category.WEB);
        insertQuestion(q110);
//Web Hard

        Question q111 = new Question("Web, Hard:The MIME text file is saved wit?h",
                "Html extension", "Html hmt extension", "Thm extension", 1,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q111);

        Question q112 = new Question("Web, Hard:White spaces in xml includes?",
                "Things link space character,new lines and tabs", "Only spaces", "Space between two double quotes", 3,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q112);
        Question q113 = new Question("Web, Hard:Xpath used to?",
                "Address your documents by specifying a location path", "Address the server", "None of these", 1,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q113);
        Question q114 = new Question("Web, Hard:DTDs are?",
                "Documents type declaration in xml", "Ways to create templates for out does type", "None of these", 2,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q114);
        Question q115 = new Question("Web, Hard:The words document node, nodelist, element nod," +
                " named node map etc.are?",
                "Interfaces", "Objects", "Elements", 1,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q115);
        Question q116 = new Question("Web, Hard:Which tag creates a number/order list?",
                "<ul>", "<ol>", "<ot>", 2,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q116);
        Question q117 = new Question("Web, Hard:The body tag usually used after?",
                "Title tag", "Head tag", "Form tag", 2,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q117);
        Question q118 = new Question("Web, Hard:Main container for<tr>,<td> and <th>is?",
                "<TABLE>", "<GROUP>", "<DATA>", 1,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q118);
        Question q119 = new Question("Web, Hard:What is athe correct html for adding " +
                "a background color?",
                "<background>yellow<background>", "<body color=yellow>", "body bg color=yellow", 3,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q119);
        Question q120 = new Question("Web, Hard:The text inside the <text area> tag works like?",
                "<p> formatted text", "<t> formatted text", "<pre> formatted text", 3,
                Question.DIFFICULTY_HARD, Category.WEB);
        insertQuestion(q120);
// Math Easy

        Question q121 = new Question("Math, Easy:4*3?",
                "15", "12", "16", 2,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q121);

        Question q122 = new Question("Math, Easy:16+18?",
                "34", "33", "32", 1,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q122);

        Question q123 = new Question("Math, Easy:8*6?",
                "45", "48", "44", 2,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q123);

        Question q124 = new Question("Math, Easy:2-4?",
                "-3", "-2", "2", 2,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q124);

        Question q125 = new Question("Math, Easy:40/8?",
                "5", "4", "3", 1,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q125);

        Question q126 = new Question("Math, Easy:50+15?",
                "66", "65", "46", 2,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q126);

        Question q127 = new Question("Math, Easy:14*6?",
                "84", "83", "85", 1,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q127);

        Question q128 = new Question("Math, Easy:16-9?",
                "8", "6", "7", 3,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q128);

        Question q129 = new Question("Math, Easy:56/4?",
                "15", "14", "13", 2,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q129);

        Question q130 = new Question("Math, Easy:47+20?",
                "66", "68", "67", 2,
                Question.DIFFICULTY_EASY, Category.Math);
        insertQuestion(q130);

        //Math Medium


        Question q131 = new Question("Math, Medium:sqrt of 16 is?",
                "4", "5", "3", 1,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q131);

        Question q132 = new Question("Math, Medium:22*8?",
                "178", "177", "176", 3,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q132);

        Question q133 = new Question("Math, Medium:Which is greater than 4? ",
                "5", "-5", "-25", 1,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q133);
        Question q134 = new Question("Math, Medium:Which is the smallest?",
                "1", "0", "-1", 1,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q134);
        Question q135 = new Question("Math, Medium:What is |-26|?",
                "-26", "26", "1", 2,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q135);
        Question q136 = new Question("Math, Medium:Find the value of 3+2(8-3)?",
                "25", "17", "24", 2,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q136);
        Question q137 = new Question("Math, Medium:the sqrt of 25 is?",
                "5", "6", "7", 1,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q137);
        Question q138 = new Question("Math, Medium:3 power 3 is ?",
                "9", "27", "21", 2,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q138);
        Question q139 = new Question("Math, Medium:4 power of 2 is?",
                "14", "16", "17", 2,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q139);
        Question q140 = new Question("Math, Medium:Which is smallest than 4?",
                "-9", "8", "-4", 1,
                Question.DIFFICULTY_MEDIUM, Category.Math);
        insertQuestion(q140);

        //Math Hard

        Question q141 = new Question("Math, Hard: Combine terms:12a + 26b -4b- 16a?",
                "4a +22b", "-28a+30b", "-4a+22b", 3,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q141);


        Question q142 = new Question("Math, Hard:Simplify: (4-5) -(13-18+2)?",
                "-1", "-2", "2", 3,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q142);

        Question q143 = new Question("Math, Hard:Factor: 3y(x-3)-2(x-3)?",
                "(x-3)(x-3)", "(x - 3)(3y - 2)", "3y(x - 3)", 2,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q143);

        Question q144 = new Question("Math, Hard:Solve for x: 2x-y =(3/4)x+6?",
                "(y+6)/5", "4(y+6)/5", "(y+6)", 2,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q144);
        Question q145 = new Question("Math, Hard:if  13*12 =651 & 41*23 =448, then, 24*22=?",
                "924", "925", "928", 1,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q145);
        Question q146 = new Question("Math, Hard:Look at this series: 22,21,23,22,24?",
                "23", "25", "26", 2,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q146);
        Question q147 = new Question("Math, Hard: Solve -15+(-5x) =0?",
                "-1", "-2", "-3", 3,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q147);
        Question q148 = new Question("Math, Hard: 888 +88 +8+8+8 =1000?",
                "100", "1000", "10000", 2,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q148);
        Question q149 = new Question("Math, Hard:The fact of 5 is?",
                "120", "130", "140", 1,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q149);
        Question q150 = new Question("Math, Hard:The fact 6 is?",
                "740", "730", "720", 3,
                Question.DIFFICULTY_HARD, Category.Math);
        insertQuestion(q150);




    }


    public void addQuestion(Question question) {


        db = getWritableDatabase();
        insertQuestion(question);

    }

    public void addQuestions(List<Question> questions) {


        db = getWritableDatabase();
        for(Question question : questions) {

            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {

        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);

    }


    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT *FROM " + CategeriesTable.TABLE_NAME, null);
        if(c.moveToFirst()) {

            do{

                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategeriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategeriesTable.COLUMN_NAME)));
                categoryList.add(category);

            }while (c.moveToNext());

        }
        c.close();
        return  categoryList;

    }

    public ArrayList<Question> getAllQuestion() {

        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {

            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }



        c.close();
        return questionList;
    }


    public ArrayList<Question> getQuestion(int categoryID,  String difficulty) {

        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

       String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
               " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
       String[] selectionArgs = new String[] {String.valueOf(categoryID), difficulty};
       Cursor c = db.query(

               QuestionsTable.TABLE_NAME,
               null,
               selection,
               selectionArgs,
               null,
               null,
               null

       );
        if (c.moveToFirst()) {

            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }



        c.close();
        return questionList;
    }



}
