package id.my.nurhamidan.epil.utils.recyclerview.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.my.nurhamidan.epil.databinding.CandidateItemBinding;
import id.my.nurhamidan.epil.databinding.VotingItemBinding;
import id.my.nurhamidan.epil.models.Candidate;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.models.Votes;
import id.my.nurhamidan.epil.models.Voting;
import id.my.nurhamidan.epil.utils.preferences.LoggedUserPreferenceManager;
import id.my.nurhamidan.epil.utils.recyclerview.holders.CandidatesHolder;
import id.my.nurhamidan.epil.utils.recyclerview.holders.VotingsHolder;
import id.my.nurhamidan.epil.viewmodels.CandidatesViewModel;
import id.my.nurhamidan.epil.viewmodels.VotesViewModel;
import id.my.nurhamidan.epil.viewmodels.VotingViewModel;
import id.my.nurhamidan.epil.views.VotingActivity;
import retrofit2.Response;

public class CandidatesAdapter extends RecyclerView.Adapter<CandidatesHolder> {
    private ArrayList<Candidate> candidates;
    private FragmentActivity activity;
    private Boolean isAddButtonShown;
    private Integer votersCount = 0;
    private Float percentage = 0.0f;

    public CandidatesAdapter(FragmentActivity activity, ArrayList<Candidate> candidates, Boolean isAddButtonShown) {
        this.candidates = candidates;
        this.activity = activity;
        this.isAddButtonShown = isAddButtonShown;

    }

    @NonNull
    @Override
    public CandidatesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CandidateItemBinding binding = CandidateItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new CandidatesHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidatesHolder holder, int position) {
        CandidateItemBinding binding = holder.getBinding();
        Candidate candidate = candidates.get(position);
        binding.candidateNameTextView.setText(candidate.getName());
        VotesViewModel viewModel = ViewModelProviders.of(this.activity).get(VotesViewModel.class);
        User user = new LoggedUserPreferenceManager(activity).getUser();
        viewModel.getResponseLiveData().observe(this.activity, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null) {
                    if (response.code() == 200) {
                        ArrayList<Votes> voters = (ArrayList<Votes>) response.body();
                        Integer votesCount = 0;
                        if (!voters.isEmpty()) {
                            Votes start = voters.get(0);
                            Votes end = voters.get(voters.size() - 1);
                            if (start == end) {
                                if (user.getId().equals(start.getUserId()) && candidate.getId().equals(start.getCandidateId())) {
                                    binding.voteMaterialButton.setText("Dipilih");
                                }
                                if (candidate.getId().equals(start.getCandidateId())) {
                                    votesCount++;
                                }
                            } else {
                                while (start != end) {
                                    Votes tmp = start;
                                    start = voters.get(voters.indexOf(start) + 1);
                                    if (user.getId().equals(tmp.getUserId()) && candidate.getId().equals(tmp.getCandidateId())) {
                                        binding.voteMaterialButton.setText("Dipilih");
                                    }
                                    if (candidate.getId().equals(tmp.getCandidateId())) {
                                        votesCount++;
                                    }
                                }
                                if (user.getId().equals(end.getUserId()) && candidate.getId().equals(end.getCandidateId())) {
                                    binding.voteMaterialButton.setText("Dipilih");
                                }
                                if (candidate.getId().equals(end.getCandidateId())) {
                                    votesCount++;
                                }
                            }
                        }
                        Integer votersCount = 0;
                        Float percentage = 0.0f;
                        for (int i = 0; i < voters.size(); i++) {
                            for (int j = 0; j < candidates.size(); j++) {
                                if (voters.get(i).getCandidateId().intValue() == candidates.get(j).getId().intValue()) {
                                    votersCount++;
                                }
                            }
                        }
                        percentage = (float) votesCount / votersCount;
                        CandidatesAdapter.this.votersCount =  votersCount;
                        binding.votesCountTextView.setText("("
                                .concat(Integer.toString(votesCount))
                                .concat(" Suara")
                                .concat(")")
                        );
                        binding.votesPercentageTextView.setText(Float.toString(percentage * 100)
                                .concat("%")
                        );
                    } else if (response.code() == 201) {
                        viewModel.getVoters();
                    } else {
                        Toast.makeText(binding.getRoot().getContext(), "Gagal mendapatkan data. " + response.code(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(binding.getRoot().getContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });
        viewModel.getVoters();

        binding.voteMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.create(user.getId(), candidate.getId());
                //Toast.makeText(binding.getRoot().getContext(), "Posisi ".concat(Integer.toString(candidate.getId())), Toast.LENGTH_LONG).show();
            }
        });
//        candidatesViewModel.getResponseLiveData().observe(activity, new Observer<Response>() {
//            @Override
//            public void onChanged(Response response) {
//                if (response != null) {
//                    if (response.code() == 204) {
//                        Toast.makeText(binding.getRoot().getContext(), "Berhasil menghapus.", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(binding.getRoot().getContext(), "sfdsfsdfdsf. ".concat(candidate.getId().toString()), Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(binding.getRoot().getContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        if (isAddButtonShown) {
            binding.deleteCandidateMaterialButton.setVisibility(View.VISIBLE);
            binding.deleteCandidateMaterialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CandidatesViewModel candidatesViewModel = ViewModelProviders.of(activity).get(CandidatesViewModel.class);
                    candidatesViewModel.delete(candidate.getId());
                }
            });
        } else {
            binding.deleteCandidateMaterialButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }
}
