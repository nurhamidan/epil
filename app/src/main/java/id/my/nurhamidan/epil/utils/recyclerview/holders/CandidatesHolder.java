package id.my.nurhamidan.epil.utils.recyclerview.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.nurhamidan.epil.databinding.CandidateItemBinding;

public class CandidatesHolder extends RecyclerView.ViewHolder {
    private CandidateItemBinding binding;

    public CandidatesHolder(CandidateItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public CandidateItemBinding getBinding() {
        return binding;
    }
}
