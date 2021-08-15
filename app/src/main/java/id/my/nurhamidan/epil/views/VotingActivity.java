package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import id.my.nurhamidan.epil.databinding.ActivityLoginBinding;
import id.my.nurhamidan.epil.databinding.ActivityVotingBinding;
import id.my.nurhamidan.epil.models.Candidate;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.models.Voting;
import id.my.nurhamidan.epil.utils.preferences.LoggedUserPreferenceManager;
import id.my.nurhamidan.epil.utils.recyclerview.adapters.CandidatesAdapter;
import id.my.nurhamidan.epil.utils.recyclerview.adapters.VotingsAdapter;
import id.my.nurhamidan.epil.viewmodels.CandidatesViewModel;
import retrofit2.Response;

public class VotingActivity extends AppCompatActivity {
    private ActivityVotingBinding binding;
    private Voting voting;
    private CandidatesViewModel viewModel;

    @Override
    protected void onResume() {
        super.onResume();
        User user = new LoggedUserPreferenceManager(this).getUser();
        if (!voting.getUserId().equals(user.getId())) {
            binding.addCandidateMaterialButton.setVisibility(View.GONE);
        }
        viewModel.getCandidates();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVotingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        voting = (Voting) intent.getSerializableExtra("voting");
        //binding.votingNameTextView.setText(voting.getName());

        viewModel = ViewModelProviders.of(this).get(CandidatesViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null) {
                    if (response.code() == 200) {
                        ArrayList<Candidate> candidates = (ArrayList<Candidate>) response.body();
                        if (!candidates.isEmpty()) {
                            Candidate start = candidates.get(0);
                            Candidate end = candidates.get(candidates.size() - 1);
                            if (start == end) {
                                if (start.getVotingId().intValue() != voting.getId().intValue()) {
                                    candidates.remove(start);
                                }
                            } else {
                                while (start != end) {
                                    Candidate tmp = start;
                                    start = candidates.get(candidates.indexOf(start) + 1);
                                    if (tmp.getVotingId().intValue() != voting.getId().intValue()) {
                                        candidates.remove(tmp);
                                    }
                                }
                                if (end.getVotingId().intValue() != voting.getId().intValue()) {
                                    candidates.remove(end);
                                }
                            }
                        }
                        binding.candidatesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        binding.candidatesRecyclerView.setAdapter(new CandidatesAdapter(VotingActivity.this, candidates, binding.addCandidateMaterialButton.isShown()));
                    } else if (response.code() == 204) {
                        Toast.makeText(getApplicationContext(), "Berhasil menghapus.", Toast.LENGTH_LONG).show();
                        viewModel.getCandidates();
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal Mendapatkan Data " + response.code(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.addCandidateMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCandidateActivity.class);
                intent.putExtra("voting", voting);
                startActivity(intent);
            }
        });
        viewModel.getCandidates();
        getSupportActionBar().setTitle(voting.getName());


    }
}