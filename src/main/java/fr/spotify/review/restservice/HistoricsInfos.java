package fr.spotify.review.restservice;

import fr.spotify.review.domain.OutputAllHistorics;

import java.util.ArrayList;

public record HistoricsInfos(ArrayList<OutputAllHistorics> histosArray) { }

