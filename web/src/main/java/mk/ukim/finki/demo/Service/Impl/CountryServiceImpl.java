package mk.ukim.finki.demo.Service.Impl;

import mk.ukim.finki.demo.Model.Country;
import mk.ukim.finki.demo.Repository.CountryRepository;
import mk.ukim.finki.demo.Service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }
}
