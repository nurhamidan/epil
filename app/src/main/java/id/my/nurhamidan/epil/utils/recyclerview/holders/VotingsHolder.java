package id.my.nurhamidan.epil.utils.recyclerview.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.nurhamidan.epil.databinding.VotingItemBinding;

public class VotingsHolder extends RecyclerView.ViewHolder {
    private VotingItemBinding binding;
    public VotingsHolder(VotingItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public VotingItemBinding getBinding() {
        return binding;
    }
}
