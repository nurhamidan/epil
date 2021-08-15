package id.my.nurhamidan.epil.utils.recyclerview.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.my.nurhamidan.epil.databinding.VotingItemBinding;
import id.my.nurhamidan.epil.models.Voting;
import id.my.nurhamidan.epil.utils.recyclerview.holders.VotingsHolder;
import id.my.nurhamidan.epil.views.VotingActivity;

public class VotingsAdapter extends RecyclerView.Adapter<VotingsHolder> {
    private ArrayList<Voting> votings;

    public VotingsAdapter(ArrayList<Voting> votings) {
        this.votings = votings;
    }

    @NonNull
    @Override
    public VotingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VotingItemBinding binding = VotingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VotingsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VotingsHolder holder, int position) {
        VotingItemBinding binding = holder.getBinding();
        Voting voting = votings.get(position);
        binding.votingNameTextView.setText(voting.getName());
        binding.showVotingMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VotingActivity.class);
                intent.putExtra("voting", voting);
                v.getContext().startActivity(intent);
                //Toast.makeText(binding.getRoot().getContext(), "Posisi ".concat(Integer.toString(voting.getId())), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return votings.size();
    }
}
