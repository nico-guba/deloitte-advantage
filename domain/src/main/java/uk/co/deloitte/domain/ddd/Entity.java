package uk.co.deloitte.domain.ddd;

public interface Entity<K extends Identity<?>> {

    K id();
}
