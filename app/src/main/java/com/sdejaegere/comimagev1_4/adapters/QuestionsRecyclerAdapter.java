package com.sdejaegere.comimagev1_4.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.activities.ManageSingleQuestionActivity;
import com.sdejaegere.comimagev1_4.model.Question;


import java.util.List;

/**
 * Créé par sdejaegere le 19/05/2019
 */
public class QuestionsRecyclerAdapter extends RecyclerView.Adapter<QuestionsRecyclerAdapter.QuestionViewHolder>{

    private Context mContext;
    private List<Question> listQuestions;

    public QuestionsRecyclerAdapter(Context context, List<Question> listQuestions) {
        this.listQuestions = listQuestions;
        this.mContext = context;
    }


    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question_recycler, parent, false);

        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, final int position) {
        holder.textViewId.setText((listQuestions.get(position).getId())+"");
        holder.textViewQuestion.setText(listQuestions.get(position).getQuestion());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idquestion = (listQuestions.get(position).getId())+"";
                Intent intent = new Intent(mContext, ManageSingleQuestionActivity.class);
                intent.putExtra("IDQUESTION", idquestion);
                intent.putExtra("TXTQUESTION", listQuestions.get(position).getQuestion());
                intent.putExtra("IDUSER", listQuestions.get(position).getUserId());
                intent.putExtra("CHOIXUN", listQuestions.get(position).getPictoUn());
                intent.putExtra("CHOIXDEUX", listQuestions.get(position).getPictoDeux());
                intent.putExtra("CHOIXTROIS", listQuestions.get(position).getPictoTrois());
                intent.putExtra("CHOIXQUATRE", listQuestions.get(position).getPictoQuatre());
                intent.putExtra("CHOIXCINQ", listQuestions.get(position).getPictoCinq());
                intent.putExtra("NOMCHOIXUN", listQuestions.get(position).getNomPictoUn());
                intent.putExtra("NOMCHOIXDEUX", listQuestions.get(position).getNomPictoDeux());
                intent.putExtra("NOMCHOIXTROIS", listQuestions.get(position).getNomPictoTrois());
                intent.putExtra("NOMCHOIXQUATRE", listQuestions.get(position).getNomPictoQuatre());
                intent.putExtra("NOMCHOIXCINQ", listQuestions.get(position).getNomPictoCinq());


                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.v(QuestionsRecyclerAdapter.class.getSimpleName(),""+listQuestions.size());
        return listQuestions.size();
    }


    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewId;
        public AppCompatTextView textViewQuestion;
        LinearLayout parentLayout;

        public QuestionViewHolder(View view) {
            super(view);
            textViewId =  view.findViewById(R.id.textViewId);
            textViewQuestion =  view.findViewById(R.id.textViewQuestion);
            parentLayout = view.findViewById(R.id.parent_layout);

        }
    }


}
